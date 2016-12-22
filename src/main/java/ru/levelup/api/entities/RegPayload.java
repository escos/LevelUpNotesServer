package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class RegPayload {
    @Expose
    private String email;
    @Expose
    private String pwdHash;
    @Expose
    private String name;

    public String getEmail() {
        return email;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public String getName() {
        return name;
    }
}
