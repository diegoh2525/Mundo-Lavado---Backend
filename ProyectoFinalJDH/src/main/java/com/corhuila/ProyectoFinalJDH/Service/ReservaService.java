package com.corhuila.ProyectoFinalJDH.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.ProyectoFinalJDH.Entity.Reserva;
import com.corhuila.ProyectoFinalJDH.Repository.ReservaRepository;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaService;

@Service
public class ReservaService implements IReservaService {

	@Autowired
	private ReservaRepository repository;

	@Override
	public List<Reserva> all() {
		return repository.findAll();
	}

	@Override
	public Optional<Reserva> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Reserva save(Reserva reserva) {
		reserva.setFechaCreacion(LocalDateTime.now());
		return repository.save(reserva);
	}

	@Override
	public void update(Reserva reserva, Long id) {
		Optional<Reserva> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Reserva r = op.get();
			r.setFechaReserva(reserva.getFechaReserva());
			r.setHoraReserva(reserva.getHoraReserva());
			r.setEstado(reserva.getEstado());
			r.setUbicacion(reserva.getUbicacion());
			r.setUsuario(reserva.getUsuario());
			r.setVehiculo(reserva.getVehiculo());
			r.setServicio(reserva.getServicio());
			r.setFechaModificacion(LocalDateTime.now());
			repository.save(r);
		}
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteLogical(Long id) {
		Optional<Reserva> op = repository.findById(id);
		if (op.isEmpty()) {
			System.out.println("Dato no encontrado");
		} else {
			Reserva r = op.get();
			r.setFechaEliminacion(LocalDateTime.now());
			repository.save(r);
		}
	}
}