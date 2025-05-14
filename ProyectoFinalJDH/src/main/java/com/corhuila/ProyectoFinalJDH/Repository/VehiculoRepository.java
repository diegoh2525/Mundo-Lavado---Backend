package com.corhuila.ProyectoFinalJDH.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{
    List<Vehiculo> findByUsuarioId(Long usuarioId);
}