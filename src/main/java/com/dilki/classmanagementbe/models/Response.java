package com.dilki.classmanagementbe.models;

public class Response {
    private String status;
    private Object result;
    private String reason;
    public Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Response() {
    }
}
