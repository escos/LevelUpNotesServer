package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class AddNotePayload {
    @Expose
    private String tittle;
    @Expose
    private String body;

    public AddNotePayload(String tittle, String body) {
        this.tittle = tittle;
        this.body = body;
    }

    public String getTittle() {
        return tittle;
    }

    public String getBody() {
        return body;
    }
}
