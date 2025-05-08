package com.corhuila.ProyectoFinalJDH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long>{

}
