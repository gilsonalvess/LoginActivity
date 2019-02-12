package com.dwm.ufg.loginactivity;

public class UserLogin {
    private String email;
    private String token;
    private String senha;

    public UserLogin() {
    }

    String getEmail() {
        return email;
    }

    String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
