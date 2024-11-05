package com.example.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Empleado;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;
    
    @GetMapping("/empleados")
    public String listadoEmpleados(Model model){
        
        List<Empleado> empleados = empleadoService.getEmpleados();

        model.addAttribute("empleados", empleados);

        return "views/listarEmpleados.html";
    }
}
