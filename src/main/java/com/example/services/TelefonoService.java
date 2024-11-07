package com.example.services;

import java.util.List;

import com.example.entities.Empleado;
import com.example.entities.Telefono;

public interface TelefonoService {
    List<Telefono> getTelefonos();
    void persistirTelefono(Telefono telefono);
    boolean existenTelefonosParaElEmpleado(Empleado empleado);
    void eliminarTelefonosDelEmpleado(Empleado empleado);
}
