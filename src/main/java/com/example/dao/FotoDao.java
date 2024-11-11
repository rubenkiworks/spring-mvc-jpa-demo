package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Empleado;
import com.example.entities.Foto;

public interface FotoDao extends JpaRepository<Foto, Integer>{
    List<Foto> findByEmpleado(Empleado empleado);
}
