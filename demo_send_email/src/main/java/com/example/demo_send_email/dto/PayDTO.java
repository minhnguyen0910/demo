package com.example.demo_send_email.dto;

public class PayDTO {
    String message;
    String status;
    String URL;

    public PayDTO() {
    }

    public PayDTO(String message, String status, String URL) {
        this.message = message;
        this.status = status;
        this.URL = URL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
