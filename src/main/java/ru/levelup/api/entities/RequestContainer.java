package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class RequestContainer<T> extends BaseRequest {
    @Expose
    private T payload;

    public T getPayLoad(){
        return payload;
    }

    public void setPayload(T payload){
        this.payload = payload;
    }
}
