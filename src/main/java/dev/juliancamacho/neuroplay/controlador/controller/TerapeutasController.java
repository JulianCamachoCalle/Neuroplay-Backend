package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.modelo.dto.TerapeutasDto;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.TerapeutasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terapeutas")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TerapeutasController {

    private final TerapeutasService terapeutasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TerapeutasDto createTerapeuta(@RequestBody TerapeutasDto terapeutasDto) {
        return terapeutasService.createTerapeutas(terapeutasDto);
    }

    @GetMapping("/{id}")
    public TerapeutasDto getTerapeutaById(@PathVariable Integer id) {
        return terapeutasService.getTerapeutas(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public TerapeutasDto getTerapeutaByUsuarioId(@PathVariable Integer usuarioId) {
        return terapeutasService.getTerapeutaByUsuarioId(usuarioId);
    }

    @GetMapping
    public List<TerapeutasDto> getAllTerapeutas() {
        return terapeutasService.getAllTerapeutas();
    }

    @GetMapping("/{id}/pacientes")
    public List<PacientesDto> getPacientesByTerapeuta(@PathVariable Integer id) {
        return terapeutasService.getPacientesByTerapeutaId(id);
    }

    @PutMapping("/{id}")
    public TerapeutasDto updateTerapeuta(@PathVariable Integer id, @RequestBody TerapeutasDto terapeutasDto) {
        return terapeutasService.updateTerapeutas(id, terapeutasDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTerapeuta(@PathVariable Integer id) {
        terapeutasService.deleteTerapeutasById(id);
    }
}