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
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String telefono;
	private boolean disponibilidad;

	@Column(name = "fecha_creacion", nullable = true)
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion", nullable = true)
	private LocalDateTime fechaEliminacion;
}