package ru.levelup.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.dao.BaseService;
import ru.levelup.enttities.User;

import java.util.List;

@Service("userService")
public class UserService extends BaseService<User, String> implements UserDAO {

    @Autowired
    public UserService(String mongoHost, String mongoDBName) {
        super(User.class, mongoHost, mongoDBName);
    }

    @Override
    public List<User> getAll() {
        return request().createQuery(User.class)
                .order("name")
                .asList();
    }

    @Override
    public List<User> get(List<String> ids) {
        return request().createQuery(User.class)
                .field("id").in(ids)
                .order("name")
                .asList();
    }

    @Override
    public User getByEmail(String email) {
        return request().createQuery(User.class)
                .field("email").equal(email)
                .get();
    }

    @Override
    public User getByToken(String token) {
        return request().createQuery(User.class)
                .field("token").equal(token)
                .get();
    }
}
