package com.corhuila.ProyectoFinalJDH.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.ReservaEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaEmpleadoRepository extends JpaRepository<ReservaEmpleado, Long>{

}