package com.corhuila.ProyectoFinalJDH.Service.IService;

import com.corhuila.ProyectoFinalJDH.Entity.Servicio;

import java.util.List;
import java.util.Optional;

public interface IServicioService {

	//Obtener todo
	public List<Servicio> all();

	//Obtener por ID
	public Optional<Servicio> findById(Long id);

	//Crear
	public Servicio save(Servicio servicio);

	//Modificar
	public void update(Servicio servicio, Long id);

	//Eliminar Físico
	public void deletePhysical(Long id);

	// Eliminar lógico
	public void deleteLogical(Long id);
}
