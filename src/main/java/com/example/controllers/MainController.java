package com.example.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestParam(name="numerosTelefono", required=false) String numerosTelefonoRecibidos){
        
        Empleado empleadocreado = empleadoService.persistirEmpleado(empleado);

        //LOG.info("numeros recibidos: " + numerosTelefonoRecibidos);

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
}
