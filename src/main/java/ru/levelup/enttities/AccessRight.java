package ru.levelup.enttities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

public class AccessRight {

    private String noteId;

    @Expose
    private int mode;

    @Expose
    private String userId;

    public AccessRight() {
    }

    public AccessRight( int mode, String userId){
        this.mode = mode;
        this.userId = userId;
    }

    public int getMode() {
        return mode;
    }

    public String getUserId() {
        return userId;
    }
}
