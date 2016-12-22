package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class DeleteNotePayload {
    @Expose
    private String noteId;

    public DeleteNotePayload(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteId() {
        return noteId;
    }
}
