package ru.levelup.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelup.dao.user.UserDAO;
import ru.levelup.dao.user.UserService;
import ru.levelup.enttities.User;
import ru.levelup.errors.TokenValidationException;

@Component("tokenValidator")
public class TokenValidator {
    public static final String[] METHODS_TOKENS_NOT_REQUIRED = new String[]{
            Method.AUTHORIZE,
            Method.REGISTRATION
    };
    private UserDAO userService;

    @Autowired
    public TokenValidator(UserService userService) {
        this.userService = userService;
    }

    public String validate(String method, String token) throws TokenValidationException {
        for (String m : METHODS_TOKENS_NOT_REQUIRED
                ) {
            if (method.equals(m)) {
                return null;
            }
        }

        if (token != null) {
            User user = userService.getByToken(token);
            return user.getId();
        }
        throw new TokenValidationException();
    }
}
