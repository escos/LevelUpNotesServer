package ru.levelup.dao.user;

import ru.levelup.dao.BaseDAO;
import ru.levelup.enttities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User, String> {

    List<User> getAll();

    List<User> get(List<String> ids);

    User getByEmail(String email);

    User getByToken(String token);
}
