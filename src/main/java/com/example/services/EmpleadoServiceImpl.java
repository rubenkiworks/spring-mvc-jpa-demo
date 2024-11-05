package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.entities.Empleado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService{

    //@Autowired
    //private EmpleadoDao empleadoDao;

    private final EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> getEmpleados() {
        
        return empleadoDao.findAll();
    }

    @Override
    public void persistirEmpleado(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    public void deleteEmpleado(Empleado empleado) {
        empleadoDao.delete(empleado);
    }

    @Override
    public Empleado getEmpleado(int idEmpleado) {
        return empleadoDao.findById(idEmpleado).get();
    }

}
