package com.corhuila.ProyectoFinalJDH.Service.IService;

import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

	//Obtener todo
	public List<Vehiculo> all();

	//Obtener por ID
	public Optional<Vehiculo> findById(Long id);

	//Crear
	public Vehiculo save(Vehiculo vehiculo);

	//Modificar
	public void update(Vehiculo vehiculo, Long id);

	//Eliminar Físico
	public void deletePhysical(Long id);

	// Eliminar lógico
	public void deleteLogical(Long id);
}
