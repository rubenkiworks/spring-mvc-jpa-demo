package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.TelefonoDao;
import com.example.entities.Empleado;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService{

    private final TelefonoDao telefonoDao;

    @Override
    public List<Telefono> getTelefonos() {
        return telefonoDao.findAll();
    }

    @Override
    public void persistirTelefono(Telefono telefono) {
        telefonoDao.save(telefono);
    }

    @Override
    public boolean existenTelefonosParaElEmpleado(Empleado empleado) {
        return telefonoDao.existsByEmpleado(empleado);
    }

    @Override
    public void eliminarTelefonosDelEmpleado(Empleado empleado) {
        telefonoDao.deleteByEmpleado(empleado);
    }

}
