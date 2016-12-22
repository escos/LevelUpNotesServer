package ru.levelup.dao.user;

import ru.levelup.dao.HibernateManager;
import ru.levelup.enttities.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserServiceSQL implements UserDAO {
    private Session session;

    public UserServiceSQL(){
        this.session = HibernateManager.getInstance().getSession();
    }

    public void add(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public void delete(User user) {
        session.beginTransaction();
        user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("id", user.getId()))
                .uniqueResult();
        if (user != null) {
            session.delete(user);
        }
        session.getTransaction().commit();
    }

    public User get(String id) {
        return (User) session.get(User.class, id);
    }

    public void update(User user) {
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public List<User> getAll() {
        List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    public List<User> get(List<String> ids) {
        List<User> users = new ArrayList<User>();
        for (String id:ids
             ) {
            users.add((User)session.get(User.class,id));
        }
        return users;
    }

    public User getByEmail(String email) {
        return (User) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public User getByToken(String token) {
        return null;
    }
}
