package com.corhuila.ProyectoFinalJDH.Service;

import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;
import com.corhuila.ProyectoFinalJDH.Repository.ReservaEmpleadoRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaEmpleadoService implements IReservaEmpleadoService {

	@Autowired
	private ReservaEmpleadoRepository repository;

	@Override
	public List<ReservaEmpleado> all() {
		return repository.findAll();
	}

	@Override
	public Optional<ReservaEmpleado> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ReservaEmpleado save(ReservaEmpleado reservaEmpleado) {
		reservaEmpleado.setFechaCreacion(LocalDateTime.now());
		return repository.save(reservaEmpleado);
	}

	@Override
	public void update(ReservaEmpleado reservaEmpleado, Long id) {
		Optional<ReservaEmpleado> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			ReservaEmpleado re = op.get();
			re.setEmpleado(reservaEmpleado.getEmpleado());
			re.setReserva(reservaEmpleado.getReserva());
			re.setFechaModificacion(LocalDateTime.now());
			repository.save(re);
		}
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteLogical(Long id) {
		Optional<ReservaEmpleado> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			ReservaEmpleado re = op.get();
			re.setFechaEliminacion(LocalDateTime.now());
			repository.save(re);
		}
	}
}