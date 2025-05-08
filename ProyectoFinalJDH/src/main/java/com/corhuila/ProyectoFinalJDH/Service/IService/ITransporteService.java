package com.corhuila.ProyectoFinalJDH.Service.IService;

import java.util.List;
import java.util.Optional;

import com.corhuila.ProyectoFinalJDH.Entity.Transporte;

public interface ITransporteService {
	
	//Obtener todo
	public List<Transporte> all();
		
	//Obtener por ID
	public Optional<Transporte> findById(Long id);
	 	
	//Crear
	public Transporte save(Transporte transporte);
		
	//Modificar
	public void update(Transporte transporte, Long id);
		
	//Eliminar Físico
	public void deletePhysical(Long id);
			
	// Eliminar lógico
	public void deleteLogical(Long id);

}
