package com.example.services;

import java.util.List;

import com.example.entities.Departamento;

public interface DepartamentoService {
    List<Departamento> getDepartamentos();
    void persistirDepartamento(Departamento departamento);
    Departamento getDepartamento(int idDepartamento);
}
