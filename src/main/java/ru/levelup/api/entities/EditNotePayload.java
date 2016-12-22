package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;
import ru.levelup.enttities.Note;

import java.util.List;

public class EditNotePayload {
    @Expose
    private String noteId;
    @Expose
    private String tittle;
    @Expose
    private String body;

    public EditNotePayload(String noteId, String tittle, String body) {
        this.noteId = noteId;
        this.tittle = tittle;
        this.body = body;
    }

    public void setId(String noteId) {
        this.noteId = noteId;
    }

    public String getId() {
        return noteId;
    }

    public String getTittle() {
        return tittle;
    }

    public String getBody() {
        return body;
    }
}
