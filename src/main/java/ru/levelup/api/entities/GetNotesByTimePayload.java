package ru.levelup.api.entities;

import com.google.gson.annotations.Expose;

public class GetNotesByTimePayload {
    @Expose
    private long startTime;
    @Expose
    private long endTime;

    public GetNotesByTimePayload(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
