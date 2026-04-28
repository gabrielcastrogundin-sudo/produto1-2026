package br.ifmg.produto1_2026.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailDTO {

    @NotNull
    @Email
    @Schema(description = "Destinatario do email")
    private String to;
    @NotBlank
    @Schema(description = "Assunto do email")
    private String subject;
    @NotBlank
    @Schema(description = "Conteudo do email")
    private String body;

    public EmailDTO(String to, String subject, String body) {}

    public EmailDTO() {}


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
