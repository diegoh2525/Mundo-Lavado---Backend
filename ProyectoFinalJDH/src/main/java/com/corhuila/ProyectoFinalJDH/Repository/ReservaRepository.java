package com.corhuila.ProyectoFinalJDH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corhuila.ProyectoFinalJDH.Entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar reservas por usuario, fecha, etc.
    // List<Reserva> findByUsuario(Usuario usuario);

}