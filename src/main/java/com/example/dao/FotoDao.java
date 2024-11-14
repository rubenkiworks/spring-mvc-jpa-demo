package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import com.example.entities.Foto;

@Repository
public interface FotoDao extends JpaRepository<Foto, Integer>{
    List<Foto> findByEmpleado(Empleado empleado);
    boolean existsByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
}
