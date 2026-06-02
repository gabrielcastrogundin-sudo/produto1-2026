package br.ifmg.produto1_2026.dto;

import jakarta.validation.constraints.NotBlank;

public class NewPasswordDTO {

    @NotBlank(message = "Campo obrigatório")
    private String token;
    @NotBlank(message = "Campo obrigatório")
    private String password;

    public NewPasswordDTO(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public NewPasswordDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
