package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.modelo.dto.PacientesDto;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.PacientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class PacientesController {

    private final PacientesService pacientesService;

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacientesDto createPacientes(@RequestBody PacientesDto pacientesDto) {
        return pacientesService.createPacientes(pacientesDto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PacientesDto getPacienteById(@PathVariable Integer id) {
        return pacientesService.getPacientes(id);
    }

    // GET PACIENTE BY USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public PacientesDto getPacienteByUsuarioId(@PathVariable Integer usuarioId) {
        return pacientesService.getPacienteByUsuarioId(usuarioId);
    }

    // GET ALL
    @GetMapping
    public List<PacientesDto> getAllPacientes() {
        return pacientesService.getAllPacientes();
    }

    // GET PACIENTE BY TERAPEUTA
    @GetMapping("/terapeuta/{terapeutaId}")
    public List<PacientesDto> getPacientesByTerapeuta(@PathVariable Integer terapeutaId) {
        return pacientesService.getPacientesByTerapeuta(terapeutaId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PacientesDto updatePaciente(@PathVariable Integer id, @RequestBody PacientesDto pacientesDto) {
        return pacientesService.updatePacientes(id, pacientesDto);
    }

    // UPDATE PROGRESO
    @PatchMapping("/{id}/progreso")
    public PacientesDto updateProgreso(@PathVariable Integer id,
                                       @RequestParam(required = false) BigDecimal progresoTotal,
                                       @RequestParam(required = false) Integer ejerciciosCompletados,
                                       @RequestParam(required = false) Integer diasConsecutivos) {
        return pacientesService.updateProgresoPaciente(id, progresoTotal, ejerciciosCompletados, diasConsecutivos);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaciente(@PathVariable Integer id) {
        pacientesService.deletePacientesById(id);
    }
}
