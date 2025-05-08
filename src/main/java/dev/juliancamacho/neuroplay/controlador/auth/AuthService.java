package dev.juliancamacho.neuroplay.controlador.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

//    private final UsuarioRepository usuarioRepository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;

//    public AuthResponse login(LoginRequest request) {
//        System.out.println("Request recibido: " + request);
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernamelogin(), request.getPasswordlogin()));
//        Usuario usuario = usuarioRepository.findByUsername(request.getUsernamelogin()).orElseThrow();
//        String token = jwtService.getToken(usuario);
//        return AuthResponse.builder()
//                .token(token)
//                .build();
//    }

//    public AuthResponse register(RegisterRequest request) {
//        Usuario usuario = User.builder()
//                .nombre(request.getNombre())
//                .apellido(request.getApellido())
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .fechaNacimiento(request.getFecha_nacimiento())
//                .genero(request.getGenero())
//                .telefono(request.getTelefono())
//                .rol(Role.PACIENTE)
//                .build();
//
//        usuarioRepository.save(usuario);
//
//        return AuthResponse.builder()
//                .token(jwtService.getToken(usuario))
//                .build();
//    }
}
