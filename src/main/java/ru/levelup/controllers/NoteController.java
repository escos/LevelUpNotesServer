package ru.levelup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.levelup.api.entities.ResponseContainer;
import ru.levelup.dao.note.NoteDAO;
import ru.levelup.enttities.AccessRight;
import ru.levelup.enttities.Note;
import ru.levelup.errors.RequestExecutionError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller("noteController")
public class NoteController {

    private NoteDAO noteService;

    @Autowired
    public NoteController(NoteDAO noteService) {
        this.noteService = noteService;
    }

    public ResponseContainer<List<Note>> getNotes(String userId)
            throws RequestExecutionError {
        List<Note> notes = noteService.getForUser(userId);
        if (notes.size() != 0) {
            return new ResponseContainer<>(notes);
        }
        throw new RequestExecutionError("GET_NOTES_ERROR");
    }

    public ResponseContainer<Note> createNote(String tittle, String body, String userId)
            throws RequestExecutionError {
        Note note = new Note();
        if (tittle != null && body != null && !tittle.isEmpty() && !body.isEmpty()) {
            note.setId(UUID.randomUUID().toString());
            note.setAuthor(userId);
            note.setTitle(tittle);
            note.setBody(body);
            note.setCreated(System.currentTimeMillis());
            note.setUpdated(note.getCreated());
            noteService.add(note);
            return new ResponseContainer<>(note);
        }
        throw new RequestExecutionError("CREATE_NOTE_ERROR");
    }

    public ResponseContainer<Note> deleteNote(String noteId, String userId)
            throws RequestExecutionError {
        if (noteId != null) {
            Note note = noteService.get(noteId);
            if (note != null && note.getAuthor().equals(userId)) {
                noteService.delete(note);
                return new ResponseContainer<>(note);
            }
        }
        throw new RequestExecutionError("DELETE_NOTE_ERROR");
    }

    public ResponseContainer<Note> editNote(String noteId, String tittle, String body, String userId)
            throws RequestExecutionError {
        if (noteId != null && tittle != null && body != null && !tittle.isEmpty() && !body.isEmpty()) {
            Note note = noteService.get(noteId);
            if (note != null) {
                List<AccessRight> accessRights = note.getAccessRights();
                if (note.getAuthor().equals(userId)) {
                    note.setBody(body);
                    note.setTitle(tittle);
                    note.setUpdated(System.currentTimeMillis());
                    noteService.update(note);
                    return new ResponseContainer<>(note);
                }
                if (accessRights.size() != 0) {
                    for (AccessRight a : accessRights
                            ) {
                        if (a.getUserId().equals(userId) && a.getMode() == 1) {
                            note.setBody(body);
                            note.setTitle(tittle);
                            note.setUpdated(System.currentTimeMillis());
                            noteService.update(note);
                            return new ResponseContainer<>(note);
                        }
                    }
                }
            }
        }
        throw new RequestExecutionError("EDIT_NOTE_ERROR");
    }

    public ResponseContainer<List<Note>> getNotesByPeriod(String userId, long startTime, long endTime)
            throws RequestExecutionError {
        List<Note> notes = noteService.getForUser(userId);
        if (notes.size() != 0 && startTime != 0L && endTime != 0L && startTime > endTime) {
            List<Note> notesSorted = new ArrayList<>();
            for (Note n : notes) {
                if (n.getCreated() > startTime && n.getCreated() < endTime) {
                    notesSorted.add(n);
                }
            }
            if (notesSorted.size() != 0) {
                return new ResponseContainer<>(notesSorted);
            }
        }
        throw new RequestExecutionError("GET_NOTES_BY_PERIOD_ERROR");
    }

    public ResponseContainer<Boolean> addAccessRight(String noteId, String userId, int mode)
            throws RequestExecutionError {
        if (noteId != null && !noteId.isEmpty() && (mode == 0 || mode == 1)) {
            Note note = noteService.get(noteId);
            if (note != null) {
                if (note.getAccessRights() == null) {
                    List<AccessRight> accessRights = new ArrayList<>();
                    accessRights.add(new AccessRight(mode, userId));
                    note.setAccessRights(accessRights);
                    noteService.update(note);
                    return new ResponseContainer<>(true);
                }
                for (AccessRight a : note.getAccessRights()) {
                    if (a.getUserId().equals(userId)) {
                        return new ResponseContainer<>(false);
                    }
                }
                note.getAccessRights().add(new AccessRight(mode, userId));
                noteService.update(note);
                return new ResponseContainer<>(true);
            }
        }
        throw new RequestExecutionError("ADD_ACCESS_RIGHT_ERROR");
    }

    public ResponseContainer<Boolean> removeAccessRight(String noteId, String userId)
            throws RequestExecutionError {
        if (noteId != null && !noteId.isEmpty()) {
            Note note = noteService.get(noteId);
            if (note != null) {
                List<AccessRight> accessRights = note.getAccessRights();
                if (accessRights.size() != 0) {
                    for (AccessRight a : accessRights) {
                        if (a.getUserId().equals(userId)) {
                            accessRights.remove(a);
                            noteService.update(note);
                            return new ResponseContainer<>(true);
                        }
                    }
                    return new ResponseContainer<>(false);
                }
            }
        }
        throw new RequestExecutionError("REMOVE_ACCESS_RIGHT_ERROR");
    }
}
