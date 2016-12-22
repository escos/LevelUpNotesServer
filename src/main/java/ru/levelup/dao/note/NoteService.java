package ru.levelup.dao.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.dao.BaseService;
import ru.levelup.enttities.Note;

import org.mongodb.morphia.query.*;

import java.util.List;

@Service("noteService")
public class NoteService extends BaseService<Note, String> implements NoteDAO {

    @Autowired
    public NoteService(String mongoHost, String mongoDBName) {
        super(Note.class, mongoHost, mongoDBName);
    }

    @Override
    public List<Note> getForUser(String id) {
        Query<Note> query = request().createQuery(Note.class);
        query.or(
                query.criteria("author").equal(id),
                query.criteria("accessRights.userId").equal(id)
        );
        return query
                .order("updated")
                .asList();
    }
}
