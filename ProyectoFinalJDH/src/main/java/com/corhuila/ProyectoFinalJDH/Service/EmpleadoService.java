package com.corhuila.ProyectoFinalJDH.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Empleado;
import com.corhuila.ProyectoFinalJDH.Repository.EmpleadoRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository repository;

	@Override
	public List<Empleado> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Empleado> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Empleado save(Empleado empleado) {
		empleado.setFechaCreacion(LocalDateTime.now());
		return repository.save(empleado);
	}

	@Override
	public void update(Empleado empleado, Long id) {
		Optional<Empleado> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Empleado e = op.get();
			e.setNombre(empleado.getNombre());
			e.setTelefono(empleado.getTelefono());
			e.setDisponible(empleado.getDisponible());
			e.setFechaModificacion(LocalDateTime.now());
			repository.save(e);
		}
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteLogical(Long id) {
		Optional<Empleado> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Empleado e = op.get();
			e.setFechaEliminacion(LocalDateTime.now());
			repository.save(e);
		}
	}
}