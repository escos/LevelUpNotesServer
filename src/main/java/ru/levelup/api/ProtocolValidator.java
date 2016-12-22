package ru.levelup.api;

import org.springframework.stereotype.Component;
import ru.levelup.api.entities.BaseRequest;
import ru.levelup.errors.ProtocolValidationException;

@Component("protocolValidator")
public class ProtocolValidator {

    public static final String[] METHODS = new String[]{
            Method.AUTHORIZE,
            Method.REGISTRATION,
            Method.GET_NOTES,
            Method.ADD_NOTE,
            Method.DELETE_NOTE,
            Method.EDIT_NOTE,
            Method.GET_ALL_USERS,
            Method.ADD_ACC_RIGHT,
            Method.REMOVE_ACC_RIGHT,
            Method.GET_NOTES_BY_PERIOD
    };

    public void validate(BaseRequest baseRequest) throws ProtocolValidationException {
        if (baseRequest.getRequestId() != null &&
                baseRequest.getMethod() != null) {
            for (String m : METHODS) {
                if (m.equals(baseRequest.getMethod())) {
                    return;
                }
            }
        }
        throw new ProtocolValidationException();
    }
}
