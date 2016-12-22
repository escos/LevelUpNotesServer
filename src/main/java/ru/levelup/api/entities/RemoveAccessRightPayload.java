package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class RemoveAccessRightPayload {
    @Expose
    private String noteId;
    @Expose
    private String email;

    public String getNoteId() {
        return noteId;
    }

    public String getEmail() {
        return email;
    }
}
