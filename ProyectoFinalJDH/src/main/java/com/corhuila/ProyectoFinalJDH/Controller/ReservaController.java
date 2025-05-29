package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.ReservaRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Reserva;
import com.corhuila.ProyectoFinalJDH.Entity.Usuario;
import com.corhuila.ProyectoFinalJDH.Entity.Vehiculo;
import com.corhuila.ProyectoFinalJDH.Entity.Servicio;
import com.corhuila.ProyectoFinalJDH.Mapper.ReservaMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaService;
import com.corhuila.ProyectoFinalJDH.Repository.UsuarioRepository;
import com.corhuila.ProyectoFinalJDH.Repository.VehiculoRepository;
import com.corhuila.ProyectoFinalJDH.Repository.ServicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping
    public ResponseEntity<List<ReservaResponse>> getAll() {
        List<ReservaResponse> list = reservaService.all().stream()
                .map(ReservaMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservaResponse> getById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(value -> ResponseEntity.ok(ReservaMapper.toResponse(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> create(@RequestBody ReservaRequest request) {
        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(request.getVehiculoId());
        Optional<Servicio> servicio = servicioRepository.findById(request.getServicioId());

        if (usuario.isEmpty() || vehiculo.isEmpty() || servicio.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Reserva reserva = ReservaMapper.toEntity(request, usuario.get(), vehiculo.get(), servicio.get());
        Reserva saved = reservaService.save(reserva);
        return ResponseEntity.ok(ReservaMapper.toResponse(saved));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaRequest request, @PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(request.getVehiculoId());
        Optional<Servicio> servicio = servicioRepository.findById(request.getServicioId());

        if (usuario.isEmpty() || vehiculo.isEmpty() || servicio.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Reserva reserva = ReservaMapper.toEntity(request, usuario.get(), vehiculo.get(), servicio.get());
        reservaService.update(reserva, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePhysical(@PathVariable Long id) {
        reservaService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic/{id}")
    public ResponseEntity<Void> deleteLogical(@PathVariable Long id) {
        reservaService.deleteLogical(id);
        return ResponseEntity.noContent().build();
    }
}
