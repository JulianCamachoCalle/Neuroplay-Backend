package dev.juliancamacho.neuroplay.controlador.controller;

import dev.juliancamacho.neuroplay.controlador.service.interfaces.ProgresoService;
import dev.juliancamacho.neuroplay.modelo.dto.ProgresoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/progreso")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProgresoController {

    private final ProgresoService progresoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgresoDto registrarProgreso(@RequestBody ProgresoDto progresoDto) {
        return progresoService.registrarProgreso(progresoDto);
    }

    @GetMapping
    public List<ProgresoDto> listarProgresos() {
        return progresoService.getAllProgresos();
    }

    @GetMapping("/{id}")
    public ProgresoDto getProgresoById(@PathVariable Integer id) {
        return progresoService.getProgresoById(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<ProgresoDto> getProgresoByPaciente(@PathVariable Integer pacienteId) {
        return progresoService.getProgresoByPaciente(pacienteId);
    }

    @GetMapping("/paciente/{pacienteId}/estadisticas")
    public Map<String, Object> getEstadisticasProgreso(@PathVariable Integer pacienteId) {
        return progresoService.getEstadisticasProgreso(pacienteId);
    }

    @PutMapping("/{id}")
    public ProgresoDto actualizarProgreso(@PathVariable Integer id, @RequestBody ProgresoDto progresoDto) {
        return progresoService.actualizarProgreso(id, progresoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProgreso(@PathVariable Integer id) {
        progresoService.eliminarProgreso(id);
    }
}
