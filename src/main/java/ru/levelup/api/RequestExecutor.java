package ru.levelup.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelup.api.entities.*;
import ru.levelup.controllers.NoteController;
import ru.levelup.controllers.UserController;
import ru.levelup.enttities.User;
import ru.levelup.errors.RequestExecutionError;

@Component("requestExecutor")
public class RequestExecutor {

    private Gson gson;
    private UserController userController;
    private NoteController noteController;

    @Autowired
    public RequestExecutor(Gson gson, UserController userController, NoteController noteController) {
        this.gson = gson;
        this.userController = userController;
        this.noteController = noteController;
    }

    public ResponseContainer execute(String json, String method, String userId) throws RequestExecutionError {

        switch (method) {
            case Method.AUTHORIZE:
                RequestContainer<AuthPayload> request;
                request = gson.fromJson(json,
                        new TypeToken<RequestContainer<AuthPayload>>() {
                        }.getType());
                return userController.authorize(request.getPayLoad().getEmail(), request.getPayLoad().getPwdHash());
            case Method.REGISTRATION:
                RequestContainer<RegPayload> request1 = gson.fromJson(json,
                        new TypeToken<RequestContainer<RegPayload>>() {
                        }.getType());
                return userController.registration(request1.getPayLoad().getEmail(),
                        request1.getPayLoad().getPwdHash(), request1.getPayLoad().getName());
            case Method.GET_ALL_USERS:
                return userController.getAllUsers();
            case Method.GET_NOTES:
                return noteController.getNotes(userId);
            case Method.ADD_NOTE:
                RequestContainer<AddNotePayload> request2 = gson.fromJson(json,
                        new TypeToken<RequestContainer<AddNotePayload>>() {
                        }.getType());
                return noteController.createNote(request2.getPayLoad().getTittle(),
                        request2.getPayLoad().getBody(), userId);
            case Method.DELETE_NOTE:
                RequestContainer<DeleteNotePayload> request3 = gson.fromJson(json,
                        new TypeToken<RequestContainer<DeleteNotePayload>>() {
                        }.getType());
                return noteController.deleteNote(request3.getPayLoad().getNoteId(), userId);
            case Method.EDIT_NOTE:
                RequestContainer<EditNotePayload> request4 = gson.fromJson(json,
                        new TypeToken<RequestContainer<EditNotePayload>>() {
                        }.getType());
                return noteController.editNote(request4.getPayLoad().getId()
                        , request4.getPayLoad().getTittle()
                        , request4.getPayLoad().getBody(), userId);
            case Method.ADD_ACC_RIGHT:
                RequestContainer<AddAccessRightPayload> request5 = gson.fromJson(json,
                        new TypeToken<RequestContainer<AddAccessRightPayload>>() {
                        }.getType());
                String id = userController.getUserIdByEmail(request5.getPayLoad().getEmail());
                return noteController.addAccessRight(request5.getPayLoad().getNoteId()
                        , id, request5.getPayLoad().getMode());
            case Method.REMOVE_ACC_RIGHT:
                RequestContainer<RemoveAccessRightPayload> request6 = gson.fromJson(json,
                        new TypeToken<RequestContainer<RemoveAccessRightPayload>>() {
                        }.getType());
                id = userController.getUserIdByEmail(request6.getPayLoad().getEmail());
                return noteController.removeAccessRight(request6.getPayLoad().getNoteId(), id);
            case Method.GET_NOTES_BY_PERIOD:
                RequestContainer<GetNotesByTimePayload> request7 = gson.fromJson(json,
                        new TypeToken<RequestContainer<GetNotesByTimePayload>>() {
                        }.getType());
                return noteController.getNotesByPeriod(userId
                        , request7.getPayLoad().getStartTime(), request7.getPayLoad().getEndTime());
        }
        throw new RequestExecutionError("method " + method + " execution error");
    }


}
