package com.upstox.test.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SubscribeResponse {
    private String event;
    private String symbol;
    private HttpStatus status;
    private String message;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "event='" + event + '\'' +
                ", symbol='" + symbol + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
