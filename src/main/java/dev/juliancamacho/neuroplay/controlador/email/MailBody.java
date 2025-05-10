package dev.juliancamacho.neuroplay.controlador.email;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
