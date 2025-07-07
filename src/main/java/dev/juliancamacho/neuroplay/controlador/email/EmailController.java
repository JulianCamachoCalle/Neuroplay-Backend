package dev.juliancamacho.neuroplay.controlador.email;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/enviarEmail")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class EmailController
{
    private final EmailSenderUtil emailSenderUtil;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendEmailMessage(@RequestBody EmailDto emailDto) {
        return emailSenderUtil.sendEmail(emailDto);
    }
}
