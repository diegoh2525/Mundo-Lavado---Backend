package com.corhuila.ProyectoFinalJDH.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Actividades;
import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;
import com.corhuila.ProyectoFinalJDH.Entity.Transporte;
import com.corhuila.ProyectoFinalJDH.Mapper.ReservaMapper;
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
		//validar si existe.            
        Optional<Reserva> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Reserva reservaUpdate = op.get();
            reservaUpdate.setUsuario(reserva.getUsuario());
            reservaUpdate.setTransportes(reserva.getTransportes());
            reservaUpdate.setAlojamientos(reserva.getAlojamientos());
            reservaUpdate.setActividades(reserva.getActividades());
            reservaUpdate.setEstado(reserva.getEstado());
            reservaUpdate.setFechaModificacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(reservaUpdate);
        }
        
        
	}

	@Override
	public void deletePhysical(Long id) {
		repository.deleteById(id);	
	}

	@Override
	public void deleteLogical(Long id) {
		//validar si existe.            
        Optional<Reserva> op = repository.findById(id);		
        
        if(op.isEmpty()){
            System.out.println("Dato no encontrado");
        }else {
        	 //Crear nuevo objeto que va a contener los datos que se van actualizar
            Reserva reservaUpdate = op.get();
            reservaUpdate.setFechaEliminacion(LocalDateTime.now());
            
            //Actualizar el objeto
            repository.save(reservaUpdate);
        }		
	}

	public int calcularCostoTotal(Reserva reserva) {
		int costoTransportes = reserva.getTransportes().stream()
				.mapToInt(Transporte::getCosto)
				.sum();

		int costoAlojamientos = reserva.getAlojamientos().stream()
				.mapToInt(Alojamiento::getCosto)
				.sum();

		int costoActividades = reserva.getActividades().stream()
				.mapToInt(Actividades::getCosto)
				.sum();

		return costoTransportes + costoAlojamientos + costoActividades;
	}

	public ReservaResponse obtenerReservaDTO(Long id) {
		Optional<Reserva> reservaOptional = repository.findById(id);

		if (reservaOptional.isEmpty()) {
			throw new RuntimeException("Reserva no encontrada");
		}

		Reserva reserva = reservaOptional.get();

		// Ya no necesitas calcular el costo manualmente, el Mapper lo hace.
		return ReservaMapper.toDTO(reserva);
	}


}