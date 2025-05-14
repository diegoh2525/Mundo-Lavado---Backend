package com.corhuila.ProyectoFinalJDH.Service.IService;

import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;

import java.util.List;
import java.util.Optional;

public interface IReservaEmpleadoService {

	//Obtener todo
	public List<ReservaEmpleado> all();

	//Obtener por ID
	public Optional<ReservaEmpleado> findById(Long id);

	//Crear
	public ReservaEmpleado save(ReservaEmpleado reservaEmpleado);

	//Modificar
	public void update(ReservaEmpleado reservaEmpleado, Long id);

	//Eliminar Físico
	public void deletePhysical(Long id);

	// Eliminar lógico
	public void deleteLogical(Long id);
}
