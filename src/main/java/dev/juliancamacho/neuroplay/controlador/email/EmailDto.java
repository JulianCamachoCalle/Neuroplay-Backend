package dev.juliancamacho.neuroplay.controlador.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String subject;

    private String message;

    private String receiver;

}