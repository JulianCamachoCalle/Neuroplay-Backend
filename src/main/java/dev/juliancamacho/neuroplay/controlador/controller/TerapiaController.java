package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapiaService;
import dev.juliancamacho.neuroplay.modelo.dto.TerapiaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terapias")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TerapiaController {

    private final TerapiaService terapiaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TerapiaDto createTerapia(@RequestBody TerapiaDto terapiaDto) {
        return terapiaService.createTerapia(terapiaDto);
    }

    @GetMapping("/{id}")
    public TerapiaDto getTerapiaById(@PathVariable Integer id) {
        return terapiaService.getTerapiaById(id);
    }

    @GetMapping
    public List<TerapiaDto> getAllTerapias() {
        return terapiaService.getAllTerapias();
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<TerapiaDto> getTerapiasByPaciente(@PathVariable Integer pacienteId) {
        return terapiaService.getTerapiasByPaciente(pacienteId);
    }

    @GetMapping("/terapeuta/{terapeutaId}")
    public List<TerapiaDto> getTerapiasByTerapeuta(@PathVariable Integer terapeutaId) {
        return terapiaService.getTerapiasByTerapeuta(terapeutaId);
    }

    @PutMapping("/{id}")
    public TerapiaDto updateTerapia(@PathVariable Integer id, @RequestBody TerapiaDto terapiaDto) {
        return terapiaService.updateTerapia(id, terapiaDto);
    }

    @PatchMapping("/{id}/estado")
    public void cambiarEstadoTerapia(@PathVariable Integer id, @RequestParam String estado) {
        terapiaService.cambiarEstadoTerapia(id, estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTerapia(@PathVariable Integer id) {
        terapiaService.deleteTerapia(id);
    }
}
