package com.example.services;

import java.util.List;

import com.example.entities.Empleado;
import com.example.entities.Foto;

public interface FotoService {
    List<Foto> getFotos();
    void persistirFoto(Foto foto);
    List<Foto> getFotosByEmpleado(Empleado empleado);
}
