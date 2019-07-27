package com.vbaslak.issuetracker.domain;

public enum Status {
    CREATED("created"),
    IN_PROGRESS("in progress"),
    RESOLVED("resolved"),
    CLOSED("closed");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

