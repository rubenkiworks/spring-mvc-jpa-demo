package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.TelefonoDao;
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

}
