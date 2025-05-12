package com.corhuila.ProyectoFinalJDH.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fechaReserva;
	private LocalTime horaReserva;
	private String estado;
	private String ubicacion;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	@ManyToOne
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;

	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion")
	private LocalDateTime fechaEliminacion;
}
