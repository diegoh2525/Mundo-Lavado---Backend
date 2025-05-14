package com.corhuila.ProyectoFinalJDH.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Servicio;
import com.corhuila.ProyectoFinalJDH.Repository.ServicioRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ServicioService implements IServicioService {

	@Autowired
	private ServicioRepository repository;

	@Override
	public List<Servicio> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Servicio> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Servicio save(Servicio servicio) {
		servicio.setFechaCreacion(LocalDateTime.now());
		return repository.save(servicio);
	}

	@Override
	public void update(Servicio servicio, Long id) {
		Optional<Servicio> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Servicio s = op.get();
			s.setNombre(servicio.getNombre());
			s.setDescripcion(servicio.getDescripcion());
			s.setPrecio(servicio.getPrecio());
			s.setFechaModificacion(LocalDateTime.now());
			repository.save(s);
		}
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteLogical(Long id) {
		Optional<Servicio> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Servicio s = op.get();
			s.setFechaEliminacion(LocalDateTime.now());
			repository.save(s);
		}
	}
}