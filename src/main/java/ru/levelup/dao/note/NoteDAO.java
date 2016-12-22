package ru.levelup.dao.note;

import ru.levelup.dao.BaseDAO;
import ru.levelup.enttities.Note;

import java.util.List;

public interface NoteDAO extends BaseDAO<Note, String> {

    List<Note> getForUser(String id);

}
