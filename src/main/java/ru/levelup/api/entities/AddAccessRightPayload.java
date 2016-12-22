package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class AddAccessRightPayload {
    @Expose
    private String noteId;
    @Expose
    private String email;
    @Expose
    private int mode;

    public AddAccessRightPayload(String noteId, String email, int mode) {
        this.noteId = noteId;
        this.email = email;
        this.mode = mode;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getEmail() {
        return email;
    }

    public int getMode() {
        return mode;
    }
}
