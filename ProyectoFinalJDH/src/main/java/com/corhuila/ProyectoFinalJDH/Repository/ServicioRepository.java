package com.corhuila.ProyectoFinalJDH.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

}