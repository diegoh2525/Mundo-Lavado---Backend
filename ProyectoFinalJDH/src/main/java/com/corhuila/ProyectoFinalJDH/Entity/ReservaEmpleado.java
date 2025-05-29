package com.corhuila.ProyectoFinalJDH.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reserva_empleado")
public class ReservaEmpleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_creacion", nullable = true)
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion", nullable = true)
	private LocalDateTime fechaEliminacion;

	@ManyToOne
	@JoinColumn(name = "reserva_id")
	private Reserva reserva;

	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
}