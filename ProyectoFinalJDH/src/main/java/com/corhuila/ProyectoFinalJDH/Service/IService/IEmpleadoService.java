package com.corhuila.ProyectoFinalJDH.Service.IService;

import com.corhuila.ProyectoFinalJDH.Entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

	//Obtener todo
	public List<Empleado> all();

	//Obtener por ID
	public Optional<Empleado> findById(Long id);

	//Crear
	public Empleado save(Empleado empleado);

	//Modificar
	public void update(Empleado empleado, Long id);

	//Eliminar Físico
	public void deletePhysical(Long id);

	// Eliminar lógico
	public void deleteLogical(Long id);
}
