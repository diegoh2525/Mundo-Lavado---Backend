package com.corhuila.ProyectoFinalJDH.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.corhuila.ProyectoFinalJDH.DTO.Request.AlojamientoRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.AlojamientoResponse;
import com.corhuila.ProyectoFinalJDH.Mapper.AlojamientoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.corhuila.ProyectoFinalJDH.Entity.Alojamiento;
import com.corhuila.ProyectoFinalJDH.Service.IService.IAlojamientoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Alojamiento")
public class AlojamientoController {

    @Autowired
    private IAlojamientoService service;

    @GetMapping()
    public List<AlojamientoResponse> all() {
        return service.all().stream()
                .map(AlojamientoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public AlojamientoResponse findById(@PathVariable Long id) {
        Optional<Alojamiento> alojamiento = service.findById(id);
        return alojamiento.map(AlojamientoMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlojamientoResponse save(@RequestBody AlojamientoRequest request) {
        Alojamiento alojamiento = AlojamientoMapper.toEntity(request);
        return AlojamientoMapper.toResponse(service.save(alojamiento));
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody AlojamientoRequest request, @PathVariable Long id) {
        Alojamiento alojamiento = AlojamientoMapper.toEntity(request);
        service.update(alojamiento, id);
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
}
