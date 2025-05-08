package com.corhuila.ProyectoFinalJDH.Service.IService;

import java.util.List;
import java.util.Optional;

import com.corhuila.ProyectoFinalJDH.Entity.Actividades;

public interface IActividadesService {
	
	//Obtener todo
	public List<Actividades> all();
		
	//Obtener por ID
	public Optional<Actividades> findById(Long id);
	 	
	//Crear
	public Actividades save(Actividades actividades);
		
	//Modificar
	public void update(Actividades actividades, Long id);
		
	//Eliminar Físico
	public void deletePhysical(Long id);
			
	// Eliminar lógico
	public void deleteLogical(Long id);

}
