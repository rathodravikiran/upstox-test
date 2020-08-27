package com.upstox.test.model;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Data
public class SubscribeResponse {
    private String event;
    private String symbol;
    private HttpStatus status;
    private String message;

}
