package dev.juliancamacho.neuroplay.service.impl;

import dev.juliancamacho.neuroplay.dto.UsuarioDto;
import dev.juliancamacho.neuroplay.entity.Usuario;
import dev.juliancamacho.neuroplay.mapper.UsuarioMapper;
import dev.juliancamacho.neuroplay.repository.TerapeutasRepository;
import dev.juliancamacho.neuroplay.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final TerapeutasRepository terapeutasRepository;

    // CREATE
    @Override
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.usuarioDtoToUsuario(usuarioDto);

//        if (usuario.getTipo() == TipoUsuario.TERAPEUTA) {
//            Terapeutas terapeuta = new Terapeutas();
//
//            usuario.setTerapeuta(terapeuta);
//            terapeuta.setUsuario(usuario);
//        } else if (usuario.getTipo() == TipoUsuario.PACIENTE) {
//            Pacientes paciente = new Pacientes();
//
//            usuario.setPaciente(paciente);
//            paciente.setUsuario(usuario);
//        }

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDto(savedUsuario);
    }

    // SELECT BY ID
    @Override
    public UsuarioDto getUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un usuario con ese ID"));
        return usuarioMapper.usuarioToUsuarioDto(usuario);
    }

    // SELECT ALL
    @Override
    public List<UsuarioDto> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream().map(
                usuarioMapper::usuarioToUsuarioDto).collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public UsuarioDto updateUsuario(Integer id, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un usuario con ese ID"));

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setFecha_nacimiento(usuarioDto.getFecha_nacimiento());
        usuario.setGenero(usuarioDto.getGenero());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setAvatar(usuarioDto.getAvatar());
        usuario.setEstado(usuarioDto.getEstado());
        usuario.setRol(usuarioDto.getRol());

        Usuario updatedUsuario = usuarioRepository.save(usuario);

        return usuarioMapper.usuarioToUsuarioDto(updatedUsuario);
    }

    // DELETE BY ID
    @Override
    public void deleteUsuarioById(Integer id) {
        usuarioRepository.deleteById(id);
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
