package dev.juliancamacho.neuroplay.controlador.auth.password;

import dev.juliancamacho.neuroplay.controlador.email.EmailService;
import dev.juliancamacho.neuroplay.controlador.email.MailBody;
import dev.juliancamacho.neuroplay.controlador.repository.UsuarioRepository;
import dev.juliancamacho.neuroplay.modelo.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    // Enviar email para verificar
    @PostMapping("/verificarEmail/{email}")
    public ResponseEntity<Map<String, String>> verificarEmail(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Ingrese un email valido"));

        int otp = otpGenerator();
        String messageBody = String.format(
                "Estimado/a %s,\n\n" +
                        "Hemos recibido una solicitud para restablecer la contraseña asociada a tu cuenta. " +
                        "Para continuar con el proceso, utiliza el siguiente código de verificación:\n\n" +
                        "%d\n\n" +
                        "Este código es válido durante los próximos 5 minutos. Si no has solicitado el restablecimiento " +
                        "de tu contraseña, puedes ignorar este mensaje.\n\n" +
                        "Si necesitas ayuda adicional, no dudes en contactarnos.\n\n" +
                        "Atentamente,\nEl equipo de Neuroplay",
                usuario.getNombre(), otp);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text(messageBody)
                .subject("token para recuperar contrasena")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .tiempoExpiracion(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .usuario(usuario)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok(Map.of("message", "Email enviado para verificar!"));
    }

    @PostMapping("/verificarOTP/{otp}/{email}")
    public ResponseEntity<Map<String, String>> verificarOTP(@PathVariable Integer otp, @PathVariable String email) {
        Usuario usuario = usuarioRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Ingrese un email valido"));

        ForgotPassword fp = forgotPasswordRepository.findOtpAndUsuario(otp, usuario)
                .orElseThrow(() -> new RuntimeException("OTP invalido para el email: " + email));

        if (fp.getTiempoExpiracion().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getIdFp());
            return new ResponseEntity<>(Map.of("error", "OTP ha expirado!"), HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok(Map.of("message", "OTP verificado"));
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<Map<String, String>> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable String email) {

        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>(Map.of("error", "Por favor ingresa tu contraseña otra vez"), HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        usuarioRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok(Map.of("message", "La contraseña ha sido cambiada"));
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
