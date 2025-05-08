package com.corhuila.ProyectoFinalJDH.Controller;

import com.corhuila.ProyectoFinalJDH.DTO.Request.TransporteRequest;
import com.corhuila.ProyectoFinalJDH.DTO.Response.TransporteResponse;
import com.corhuila.ProyectoFinalJDH.Entity.Transporte;
import com.corhuila.ProyectoFinalJDH.Mapper.TransporteMapper;
import com.corhuila.ProyectoFinalJDH.Service.IService.ITransporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ProyectoFinalJDH/Transporte")
public class TransporteController {

    @Autowired
    private ITransporteService service;

    @GetMapping()
    public List<TransporteResponse> all() {
        return service.all().stream()
                .map(TransporteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TransporteResponse findById(@PathVariable Long id) {
        Optional<Transporte> op = service.findById(id);
        if (op.isEmpty()) {
            throw new RuntimeException("Transporte no encontrado");
        }
        return TransporteMapper.toResponse(op.get());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransporteResponse save(@RequestBody TransporteRequest request) {
        Transporte transporte = TransporteMapper.toEntity(request);
        Transporte saved = service.save(transporte);
        return TransporteMapper.toResponse(saved);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody TransporteRequest request, @PathVariable Long id) {
        Transporte transporte = TransporteMapper.toEntity(request);
        service.update(transporte, id);
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
