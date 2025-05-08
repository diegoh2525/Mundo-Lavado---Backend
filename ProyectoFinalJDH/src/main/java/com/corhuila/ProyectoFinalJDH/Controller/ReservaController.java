package com.corhuila.ProyectoFinalJDH.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.corhuila.ProyectoFinalJDH.DTO.Response.ReservaResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Reserva;
import com.corhuila.ProyectoFinalJDH.Service.IService.IReservaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Reserva")
public class ReservaController {

	@Autowired
	private IReservaService service;

	@GetMapping()
    public List<Reserva> all() {
        return service.all();
    }

	 @GetMapping("{id}")
    public Optional<Reserva> findById(@PathVariable Long id) {
        return service.findById(id);
    }

	 @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Reserva save(@RequestBody Reserva reserva) {
        return service.save(reserva);
    }

	 @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody Reserva reserva, @PathVariable Long id) {
        service.update(reserva, id);
    }

	 @PutMapping("deleteLogical/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteLogical(@PathVariable Long id) {
        service.deleteLogical(id);
    }

	 @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePhysical(@PathVariable Long id) {
        service.deletePhysical(id);
    }

    @GetMapping("/detalle/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ReservaResponse obtenerDetalleReserva(@PathVariable Long id) {
        return service.obtenerReservaDTO(id);
    }


}