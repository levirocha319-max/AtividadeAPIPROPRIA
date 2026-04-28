package com.example.biblioteca.dto;

public class RespostaAutenticacao {

    private String token;
    private String tipo = "Bearer";

    public RespostaAutenticacao(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
