package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.FotoDao;
import com.example.entities.Empleado;
import com.example.entities.Foto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FotoServiceImpl implements FotoService{

    private final FotoDao fotoDao;

    @Override
    public List<Foto> getFotos() {
        return fotoDao.findAll();
    }

    @Override
    public void persistirFoto(Foto foto) {
        fotoDao.save(foto);
    }

    @Override
    public List<Foto> getFotosByEmpleado(Empleado empleado) {
        return fotoDao.findByEmpleado(empleado);
    }

    @Override
    public boolean existenFotosParaElEmpleado(Empleado empleado) {
        return fotoDao.existsByEmpleado(empleado);
    }

    @Override
    public void eliminarFotosDelEmpleado(Empleado empleado) {
        fotoDao.deleteByEmpleado(empleado);
    }

}
