package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class AuthPayload {
    @Expose
    private String email;
    @Expose
    private String pwdHash;

    public AuthPayload(String email, String pwdHash) {
        this.email = email;
        this.pwdHash = pwdHash;
    }

    public String getEmail() {
        return email;
    }

    public String getPwdHash() {
        return pwdHash;
    }
}
