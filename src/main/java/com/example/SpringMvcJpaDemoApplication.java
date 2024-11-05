package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.models.Genero;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringMvcJpaDemoApplication implements CommandLineRunner{

	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;
	private final TelefonoService telefonoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creamos registros de muestra

		// Creamos departamentos primero
		departamentoService.persistirDepartamento(
			Departamento.builder()
			.nombre("INFORMATICA")
			.id(1)
			.build()
		);

		departamentoService.persistirDepartamento(
			Departamento.builder()
			.nombre("RRHH")
			.id(2)
			.build()
		);

		// CREAMOS EMPLEADOS
		empleadoService.persistirEmpleado(
			Empleado.builder()
			.nombre("Ruben")
			.primerApellido("Gomez")
			.departamento(departamentoService.getDepartamento(1))
			.fechaAlta(LocalDate.of(2000, Month.SEPTEMBER, 1))
			.salario(3500.5)
			.genero(Genero.HOMBRE)
			.id(1)
			.build()
		);

		empleadoService.persistirEmpleado(
			Empleado.builder()
			.nombre("Maria")
			.primerApellido("Gomez")
			.departamento(departamentoService.getDepartamento(2))
			.fechaAlta(LocalDate.of(2000, Month.APRIL, 2))
			.salario(2500.5)
			.genero(Genero.MUJER)
			.id(2)
			.build()
		);

		telefonoService.persistirTelefono(
			Telefono.builder()
			.numero("666666666")
			.id(1)
			.empleado(empleadoService.getEmpleado(1))
			.build()
		);

		telefonoService.persistirTelefono(
			Telefono.builder()
			.numero("777777777")
			.id(2)
			.empleado(empleadoService.getEmpleado(1))
			.build()
		);

		telefonoService.persistirTelefono(
			Telefono.builder()
			.numero("888888888")
			.id(3)
			.empleado(empleadoService.getEmpleado(2))
			.build()
		);
	}

}
