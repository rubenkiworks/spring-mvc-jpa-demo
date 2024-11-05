package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    
    @GetMapping("/empleados")
    public ModelAndView listadoEmpleados(){
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("views/listarEmpleados.html");

        return mav;
    }
}
