package com.corhuila.ProyectoFinalJDH.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate fechaReserva;
	private LocalTime horaReserva;
	private String estado;
	private String ubicacion;

	@Column(name = "fecha_creacion", nullable = true)
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion", nullable = true)
	private LocalDateTime fechaEliminacion;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	@ManyToOne
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;
}