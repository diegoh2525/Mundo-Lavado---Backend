package com.corhuila.ProyectoFinalJDH.Entity;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToMany
	@JoinTable(
			name = "reserva_transporte",
			joinColumns = @JoinColumn(name = "reserva_id"),
			inverseJoinColumns = @JoinColumn(name = "transporte_id")
	)
	private List<Transporte> transportes;

	@ManyToMany
	@JoinTable(
			name = "reserva_alojamiento",
			joinColumns = @JoinColumn(name = "reserva_id"),
			inverseJoinColumns = @JoinColumn(name = "alojamiento_id")
	)
	private List<Alojamiento> alojamientos;

	@ManyToMany
	@JoinTable(
			name = "reserva_actividades",
			joinColumns = @JoinColumn(name = "reserva_id"),
			inverseJoinColumns = @JoinColumn(name = "actividades_id")
	)
	private List<Actividades> actividades;

	@Column(name = "estado")
	private Boolean estado;

	@Column(name = "fecha_viaje")
	private Date fechaViaje;

	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion")
	private LocalDateTime fechaEliminacion;

}
