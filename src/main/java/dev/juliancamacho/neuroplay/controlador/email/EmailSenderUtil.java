package dev.juliancamacho.neuroplay.controlador.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailSenderUtil {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}}")
    private String password;

    public String sendEmail(EmailDto emailDto) {
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(true);
            email.setStartTLSEnabled(true);
            email.setFrom(username);
            email.setSubject(emailDto.getSubject());
            email.setMsg(emailDto.getMessage());
            email.addTo(emailDto.getReceiver());

            // Adjuntar archivo si existe
            if (emailDto.getFile() != null && !emailDto.getFile().isEmpty()) {
                File tempFile = File.createTempFile("temp", emailDto.getFile().getOriginalFilename());
                emailDto.getFile().transferTo(tempFile);

                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(tempFile.getAbsolutePath());
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("Archivo adjunto");
                attachment.setName(emailDto.getFile().getOriginalFilename());
                email.attach(attachment);

                tempFile.deleteOnExit();
            }

            email.send();
            return "Email Sent";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "Failed to send email";
        }
    }
}
