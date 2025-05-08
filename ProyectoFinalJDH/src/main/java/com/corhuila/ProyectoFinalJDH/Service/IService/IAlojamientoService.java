package com.corhuila.ProyectoFinalJDH.Service.IService;

import java.util.List;
import java.util.Optional;

import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;

public interface IAlojamientoService {
	
	//Obtener todo
	public List<Alojamiento> all();
		
	//Obtener por ID
	public Optional<Alojamiento> findById(Long id);
	 	
	//Crear
	public Alojamiento save(Alojamiento alojamiento);
		
	//Modificar
	public void update(Alojamiento alojamiento, Long id);
		
	//Eliminar Físico
	public void deletePhysical(Long id);
			
	// Eliminar lógico
	public void deleteLogical(Long id);

}
