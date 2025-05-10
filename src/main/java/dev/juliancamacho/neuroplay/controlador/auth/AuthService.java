package dev.juliancamacho.neuroplay.controlador.auth;

import dev.juliancamacho.neuroplay.controlador.jwt.JwtService;
import dev.juliancamacho.neuroplay.controlador.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import dev.juliancamacho.neuroplay.modelo.enums.EstadoUsuario;
import dev.juliancamacho.neuroplay.modelo.enums.Role;
import dev.juliancamacho.neuroplay.modelo.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        System.out.println("Request recibido: " + request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernamelogin(), request.getPasswordlogin()));
        Usuario usuario = usuarioRepository.findByUsername(request.getUsernamelogin()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .genero(request.getGenero())
                .fechaNacimiento(request.getFechaNacimiento())
                .avatar("default.png")
                .estado(EstadoUsuario.ACTIVO)
                .rol(Role.PACIENTE)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
