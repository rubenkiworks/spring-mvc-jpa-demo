package com.example.services;

import java.util.List;

import com.example.entities.Empleado;

public interface EmpleadoService {
    List<Empleado> getEmpleados();

    void persistirEmpleado(Empleado empleado);

    void deleteEmpleado(Empleado empleado);

    Empleado getEmpleado(int idEmpleado);
}
