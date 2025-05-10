package dev.juliancamacho.neuroplay.controlador.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/enviarEmail")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class EmailController
{
    private final EmailSenderUtil emailSenderUtil;

    @PostMapping
    public String sendEmailMessage(@RequestBody EmailDto emailDto,
                                   @RequestParam(value = "archivo", required = false) MultipartFile file) {
        return emailSenderUtil.sendEmail(emailDto, file);
    }
}
