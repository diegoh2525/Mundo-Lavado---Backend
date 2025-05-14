package com.corhuila.ProyectoFinalJDH.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import com.corhuila.ProyectoFinalJDH.Repository.VehiculoRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class VehiculoService implements IVehiculoService {

	@Autowired
	private VehiculoRepository repository;

	@Override
	public List<Vehiculo> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Vehiculo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		vehiculo.setFechaCreacion(LocalDateTime.now());
		return repository.save(vehiculo);
	}

	@Override
	public void update(Vehiculo vehiculo, Long id) {
		Optional<Vehiculo> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Vehiculo v = op.get();
			v.setMarca(vehiculo.getMarca());
			v.setModelo(vehiculo.getModelo());
			v.setPlaca(vehiculo.getPlaca());
			v.setTipo(vehiculo.getTipo());
			v.setUsuario(vehiculo.getUsuario());
			v.setFechaModificacion(LocalDateTime.now());
			repository.save(v);
		}
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteLogical(Long id) {
		Optional<Vehiculo> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Vehiculo v = op.get();
			v.setFechaEliminacion(LocalDateTime.now());
			repository.save(v);
		}
	}
}