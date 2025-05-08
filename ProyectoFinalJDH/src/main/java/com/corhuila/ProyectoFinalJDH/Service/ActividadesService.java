package com.corhuila.ProyectoFinalJDH.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Actividades;
import com.corhuila.ProyectoFinalJDH.Repository.ActividadesRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IActividadesService;

@Service
public class ActividadesService implements IActividadesService {

	@Autowired
	private ActividadesRepository repository;
	
	@Override
	public List<Actividades> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Actividades> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Actividades save(Actividades actividades) {
		actividades.setFechaCreacion(LocalDateTime.now());
		return repository.save(actividades);
	}

	@Override
	public void update(Actividades actividades, Long id) {
		//validar si existe.            
        Optional<Actividades> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Actividades actividadesUpdate = op.get();
            actividadesUpdate.setNombre(actividades.getNombre());
            actividadesUpdate.setDescripcion(actividades.getDescripcion());
            actividadesUpdate.setCosto(actividades.getCosto());
            actividadesUpdate.setFechaModificacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(actividadesUpdate);
        }
        
        
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public void deleteLogical(Long id) {
		//validar si existe.            
        Optional<Actividades> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Actividades actividadesUpdate = op.get();
            actividadesUpdate.setFechaEliminacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(actividadesUpdate);
        }		
	}	
}