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
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String marca;
	private String modelo;
	private String placa;
	private String tipo;

	@Column(name = "fecha_creacion", nullable = true)
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;

	@Column(name = "fecha_eliminacion", nullable = true)
	private LocalDateTime fechaEliminacion;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}