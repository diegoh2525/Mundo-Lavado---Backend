package com.corhuila.ProyectoFinalJDH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Actividades;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Long>{

}