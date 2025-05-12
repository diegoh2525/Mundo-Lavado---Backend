package com.corhuila.ProyectoFinalJDH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}