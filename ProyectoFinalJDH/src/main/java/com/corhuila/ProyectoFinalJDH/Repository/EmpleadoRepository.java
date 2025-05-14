package com.corhuila.ProyectoFinalJDH.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    List<Empleado> findByDisponibleTrue();

}