package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService{

    private final DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> getDepartamentos() {
        return departamentoDao.findAll();
    }

    @Override
    public void persistirDepartamento(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    public Departamento getDepartamento(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

}
