package com.corhuila.ProyectoFinalJDH.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Transporte;
import com.corhuila.ProyectoFinalJDH.Repository.TransporteRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.ITransporteService;

@Service
public class TransporteService implements ITransporteService {

	@Autowired
	private TransporteRepository repository;
	
	@Override
	public List<Transporte> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Transporte> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Transporte save(Transporte transporte) {
		transporte.setFechaCreacion(LocalDateTime.now());
		return repository.save(transporte);
	}

	@Override
	public void update(Transporte transporte, Long id) {
		//validar si existe.            
        Optional<Transporte> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Transporte transporteUpdate = op.get();
            transporteUpdate.setTipo(transporte.getTipo());
            transporteUpdate.setEmpresa(transporte.getEmpresa());
            transporteUpdate.setCosto(transporte.getCosto());
            transporteUpdate.setFechaModificacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(transporteUpdate);
        }
        
        
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public void deleteLogical(Long id) {
		//validar si existe.            
        Optional<Transporte> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Transporte transporteUpdate = op.get();
            transporteUpdate.setFechaEliminacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(transporteUpdate);
        }		
	}	
}
