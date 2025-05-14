package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.controlador.service.interfaces.SesionService;
import dev.juliancamacho.neuroplay.modelo.dto.SesionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class SesionController {

    private final SesionService sesionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionDto registrarSesion(@RequestBody SesionDto sesionDto) {
        return sesionService.registrarSesion(sesionDto);
    }

    @GetMapping("/{id}")
    public SesionDto getSesionById(@PathVariable Integer id) {
        return sesionService.getSesionById(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<SesionDto> getSesionesByPaciente(@PathVariable Integer pacienteId) {
        return sesionService.getSesionesByPaciente(pacienteId);
    }

    @PutMapping("/{id}")
    public SesionDto actualizarSesion(@PathVariable Integer id, @RequestBody SesionDto sesionDto) {
        return sesionService.actualizarSesion(id, sesionDto);
    }

    @PatchMapping("/{id}/completada")
    public void marcarComoCompletada(@PathVariable Integer id, @RequestParam boolean completada) {
        sesionService.marcarComoCompletada(id, completada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarSesion(@PathVariable Integer id) {
        sesionService.eliminarSesion(id);
    }
}
