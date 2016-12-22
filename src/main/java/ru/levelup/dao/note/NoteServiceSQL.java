package ru.levelup.dao.note;

import ru.levelup.dao.HibernateManager;
import ru.levelup.enttities.AccessRight;
import ru.levelup.enttities.Note;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class NoteServiceSQL implements NoteDAO {
    private Session session;

    public NoteServiceSQL() {
        this.session = HibernateManager.getInstance().getSession();
    }

    public void add(Note note) {
        session.beginTransaction();
        session.save(note);
        session.getTransaction().commit();
    }

    public void delete(Note note) {
        session.beginTransaction();
        note = (Note) session.createCriteria(Note.class)
                .add(Restrictions.eq("id", note.getId()))
                .uniqueResult();
        if (note != null) {
            session.delete(note);
        }
        session.getTransaction().commit();
    }

    public Note get(String id) {
        return (Note) session.get(Note.class, id);
    }

    public void update(Note note) {
        session.beginTransaction();
        session.update(note);
        session.getTransaction().commit();
    }

    public List<Note> getForUser(String id) {
        Criterion cr1 = Restrictions.eq("author", id);
        Criterion cr2 = Restrictions.eq("userId", id);
        return session.createCriteria(Note.class).add(Restrictions.or(cr1, cr2)).list();
    }
}
