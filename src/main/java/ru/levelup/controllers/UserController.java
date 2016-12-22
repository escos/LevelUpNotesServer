package ru.levelup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.levelup.api.entities.ResponseContainer;
import ru.levelup.dao.user.UserDAO;
import ru.levelup.enttities.User;
import ru.levelup.errors.RequestExecutionError;

import java.util.List;
import java.util.UUID;

@Controller("userController")
public class UserController {
    private UserDAO userService;

    @Autowired
    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    public ResponseContainer<String> authorize(String email, String pwdHash)
            throws RequestExecutionError {
        User user = userService.getByEmail(email);
        if (user != null && user.getPwdHash().equals(pwdHash)) {
            user.setToken(UUID.randomUUID().toString() + UUID.randomUUID().toString());
            userService.update(user);
            return new ResponseContainer<>(user.getToken());
        }
        throw new RequestExecutionError("AUTHORIZE_ERROR");
    }

    public ResponseContainer<String> registration(String email, String pwdHash, String name)
            throws RequestExecutionError {
        User user = new User();
        if (userService.getByEmail(email) == null && email != null && pwdHash != null && name != null) {
            if (!email.isEmpty() && !pwdHash.isEmpty() && !name.isEmpty()) {
                user.setToken(UUID.randomUUID().toString() + UUID.randomUUID().toString());
                user.setId(UUID.randomUUID().toString());
                user.setName(name);
                user.setEmail(email);
                user.setPwdHash(pwdHash);
                userService.add(user);
                return new ResponseContainer<>(user.getToken());
            }
        }
        throw new RequestExecutionError("REGISTRATION_ERROR");
    }

    public ResponseContainer<List<User>> getAllUsers() {
        return new ResponseContainer<>(userService.getAll());
    }

    public String getUserIdByEmail(String email) throws RequestExecutionError{
        if (email != null && !email.isEmpty()) {
            if (userService.getByEmail(email)!=null) {
                return userService.getByEmail(email).getId();
            }
        }
        throw  new RequestExecutionError("GET_ID_BY_EMAIL_ERROR");
    }

    public ResponseContainer<User> getUser(String userId) {
        return new ResponseContainer<>(userService.get(userId));
    }

    public ResponseContainer<User> getUserByEmail(String email) {
        return new ResponseContainer<>(userService.get(email));
    }
}
