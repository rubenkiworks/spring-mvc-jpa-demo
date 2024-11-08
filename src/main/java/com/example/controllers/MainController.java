package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;
    private final TelefonoService telefonoService;

    private static final Logger LOG = Logger.getLogger("MainController");
    
    @GetMapping("/empleados")
    public String listadoEmpleados(Model model){
        
        List<Empleado> empleados = empleadoService.getEmpleados();

        model.addAttribute("empleados", empleados);

        return "views/listarEmpleados.html";
    }

    @GetMapping("/alta")
    public String frmAlta(Model model){
        Empleado empleado = new Empleado(); 
        model.addAttribute("empleado", empleado);

        List<Departamento> departamentos = departamentoService.getDepartamentos();
        model.addAttribute("departamentos", departamentos);

        return "views/formularioDeAltaModificacion";
    }

    @PostMapping("/guardarEmpleado")
    @Transactional
    public String guardarEmpleado(@ModelAttribute(name="empleado") Empleado empleado,
    @RequestParam(name="numerosTelefono", required=false) String numerosTelefonoRecibidos,
    @RequestParam(name="imagen", required=false) MultipartFile imagenRecibida){
        
        if (!imagenRecibida.isEmpty()) {
            Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes\\");

            String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();

            Path rutaCompleta = Paths.get(rutaAbsoluta + "\\" + imagenRecibida.getOriginalFilename());

            try {
                byte[] archivoDeImagenEnBytes = imagenRecibida.getBytes();
                Files.write(rutaCompleta, archivoDeImagenEnBytes);

                empleado.setFoto(imagenRecibida.getOriginalFilename());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Empleado empleadocreado = empleadoService.persistirEmpleado(empleado);

        //LOG.info("numeros recibidos: " + numerosTelefonoRecibidos);

        if(telefonoService.existenTelefonosParaElEmpleado(empleadocreado)){
            telefonoService.eliminarTelefonosDelEmpleado(empleadocreado);
        }
        
        if (numerosTelefonoRecibidos != null && !numerosTelefonoRecibidos.isEmpty()) {
            String[] arrayNumeros = numerosTelefonoRecibidos.split(";");
            List<String> numeros = Arrays.asList(arrayNumeros);

            numeros.stream().forEach(n -> {
                Telefono telefono =  Telefono.builder()
                .numero(n)
                .empleado(empleadocreado)
                .build();

                telefonoService.persistirTelefono(telefono);
            });
        }
        
        return "redirect:/empleados";
    }

    @GetMapping("/modificar/{id}")
    public String updateEmpleado(Model model, @PathVariable(name="id", required=true) int idEmpleado){

        Empleado empleado = empleadoService.getEmpleado(idEmpleado);
        model.addAttribute("empleado", empleado);

        List<Telefono> telefonos = telefonoService.getTelefonos();

        List<Telefono> telefonosEmpleado = telefonos.stream()
        .filter(t -> t.getEmpleado().getId() == idEmpleado)
        .collect(Collectors.toList());

        String numeros = telefonosEmpleado.stream()
        .map(Telefono::getNumero)
        .collect(Collectors.joining(";"));

        model.addAttribute("numeros", numeros);

        List<Departamento> departamentos = departamentoService.getDepartamentos();
        model.addAttribute("departamentos", departamentos);

        return "views/formularioDeAltaModificacion";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(Model model, @PathVariable(name="id", required=true) int idEmpleado){
        
        model.addAttribute("empleado", empleadoService.getEmpleado(idEmpleado));

        return "views/detallesEmpleado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(Model model, @PathVariable(name="id", required=true) int idEmpleado){

        empleadoService.deleteEmpleado(empleadoService.getEmpleado(idEmpleado));

        return "redirect:/empleados";
    }
}
