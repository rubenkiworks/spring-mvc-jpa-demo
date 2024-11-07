package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import com.example.entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer>{
    boolean existsByEmpleado(Empleado empleado);
    List<Telefono> findByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
}
