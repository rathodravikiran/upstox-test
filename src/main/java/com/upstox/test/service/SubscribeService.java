package com.upstox.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface SubscribeService {
    public ResponseEntity<?> clientSubscribe(String subscribeData) throws IOException;
}
