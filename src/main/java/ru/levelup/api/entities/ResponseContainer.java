package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class ResponseContainer<T> {
    @Expose
    private String requestId;
    @Expose
    private int code;
    @Expose
    private T payload;

    public ResponseContainer(T payload) {
        this.payload = payload;
    }

    public ResponseContainer(int code, T payload) {
        this.code = code;
        this.payload = payload;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }
}
