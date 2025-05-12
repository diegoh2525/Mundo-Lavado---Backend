package com.corhuila.ProyectoFinalJDH.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "correo", length = 50, nullable = false, unique = true)
	private String correo;
	
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	
	@Column(name = "fecha_creacion", nullable = true)
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_modificacion", nullable = true)
	private LocalDateTime fechaModificacion;
	
	@Column(name = "fecha_eliminacion", nullable = true)
	private LocalDateTime fechaEliminacion;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Vehiculo> vehiculos;

}