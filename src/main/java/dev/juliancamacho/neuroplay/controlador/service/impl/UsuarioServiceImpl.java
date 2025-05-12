package dev.juliancamacho.neuroplay.controlador.service.impl;

import dev.juliancamacho.neuroplay.controlador.repository.PacientesRepository;
import dev.juliancamacho.neuroplay.controlador.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.modelo.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import dev.juliancamacho.neuroplay.modelo.enums.Role;
import dev.juliancamacho.neuroplay.modelo.mapper.UsuarioMapper;
import dev.juliancamacho.neuroplay.controlador.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.controlador.service.interfaces.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final TerapeutasRepository terapeutasRepository;
    private final PacientesRepository pacientesRepository;

    // CREATE
    @Override
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        // Validaciones
        if (usuarioRepository.existsByUsername(usuarioDto.getUsername())) {
            throw new IllegalStateException("El email ya está registrado");
        }
        if (usuarioRepository.existsByTelefono(usuarioDto.getTelefono())) {
            throw new IllegalStateException("El teléfono ya está registrado");
        }

        Usuario usuario = usuarioMapper.usuarioDtoToUsuario(usuarioDto);
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDto(savedUsuario);
    }

    // SELECT BY ID
    @Override
    public UsuarioDto getUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return buildCompleteUsuarioDto(usuario);
    }

    // SELECT BY USERNAME/EMAIL
    @Override
    public UsuarioDto getUsuarioByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return buildCompleteUsuarioDto(usuario);
    }

    // SELECT ALL
    @Override
    public List<UsuarioDto> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::buildCompleteUsuarioDto)
                .collect(Collectors.toList());
    }

    // SELECT BY ROLE
    @Override
    public List<UsuarioDto> getUsuariosByRole(Role role) {
        return usuarioRepository.findByRol(role).stream()
                .map(this::buildCompleteUsuarioDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public UsuarioDto updateUsuario(Integer id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Validar unicidad de username/email si cambió
        if (!usuario.getUsername().equals(usuarioDto.getUsername()) &&
                usuarioRepository.existsByUsername(usuarioDto.getUsername())) {
            throw new IllegalStateException("El email ya está registrado");
        }

        // Validar unicidad de teléfono si cambió
        if (!usuario.getTelefono().equals(usuarioDto.getTelefono()) &&
                usuarioRepository.existsByTelefono(usuarioDto.getTelefono())) {
            throw new IllegalStateException("El teléfono ya está registrado");
        }

        // Actualizar campos
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
        usuario.setGenero(usuarioDto.getGenero());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setAvatar(usuarioDto.getAvatar());
        usuario.setEstado(usuarioDto.getEstado());
        usuario.setRol(usuarioDto.getRol());

        // Actualizar password solo si se proporcionó una nueva
        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDto(updatedUsuario);
    }

    // DELETE BY ID
    @Override
    public void deleteUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Validar que no tenga registros asociados
        if (usuario.getRol() == Role.TERAPEUTA && terapeutasRepository.existsByUsuarioId(id)) {
            throw new IllegalStateException("No se puede eliminar, el usuario tiene registros como terapeuta");
        }

        if (usuario.getRol() == Role.PACIENTE && pacientesRepository.existsByUsuarioId(id)) {
            throw new IllegalStateException("No se puede eliminar, el usuario tiene registros como paciente");
        }

        usuarioRepository.delete(usuario);
    }

    // Métodos auxiliares
    private UsuarioDto buildCompleteUsuarioDto(Usuario usuario) {
        UsuarioDto dto = usuarioMapper.usuarioToUsuarioDto(usuario);

        // Ocultar password por seguridad
        dto.setPassword(null);

        // Agregar info específica según rol
        if (usuario.getRol() == Role.TERAPEUTA) {
            terapeutasRepository.findByUsuarioId(usuario.getId()).ifPresent(terapeuta -> {
                dto.setEspecialidad(terapeuta.getEspecialidad());
                dto.setLicencia(terapeuta.getLicencia());
            });
        }

        if (usuario.getRol() == Role.PACIENTE) {
            pacientesRepository.findByUsuarioId(usuario.getId()).ifPresent(paciente -> {
                dto.setTerapeutaId(paciente.getTerapeuta().getId());
                dto.setFechaACV(paciente.getFechaAcv());
            });
        }

        return dto;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean checkTelefonoExists(String telefono) {
        return usuarioRepository.existsByTelefono(telefono);
    }
    }
