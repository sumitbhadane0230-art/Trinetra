package com.trinetra.personnel.models;

public class ChatRequest {

    private String personnel_id;
    private String message;

    public ChatRequest(
            String personnelId,
            String message
    ) {
        this.personnel_id = personnelId;
        this.message = message;
    }

    public String getPersonnelId() {
        return personnel_id;
    }

    public String getMessage() {
        return message;
    }
}