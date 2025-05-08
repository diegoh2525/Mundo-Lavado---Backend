package com.corhuila.ProyectoFinalJDH.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;
import com.corhuila.ProyectoFinalJDH.Repository.AlojamientoRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IAlojamientoService;

@Service
public class AlojamientoService implements IAlojamientoService {

	@Autowired
	private AlojamientoRepository repository;
	
	@Override
	public List<Alojamiento> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Alojamiento> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Alojamiento save(Alojamiento alojamiento) {
		alojamiento.setFechaCreacion(LocalDateTime.now());
		return repository.save(alojamiento);
	}

	@Override
	public void update(Alojamiento alojamiento, Long id) {
		//validar si existe.            
        Optional<Alojamiento> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Alojamiento alojamientoUpdate = op.get();
            alojamientoUpdate.setNombre(alojamiento.getNombre());
            alojamientoUpdate.setTipo(alojamiento.getTipo());
            alojamientoUpdate.setDescripcion(alojamiento.getDescripcion());
            alojamientoUpdate.setFechaModificacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(alojamientoUpdate);
        }
        
        
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public void deleteLogical(Long id) {
		//validar si existe.            
        Optional<Alojamiento> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Alojamiento alojamientoUpdate = op.get();
            alojamientoUpdate.setFechaEliminacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(alojamientoUpdate);
        }		
	}	
}
