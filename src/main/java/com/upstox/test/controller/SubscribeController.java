package com.upstox.test.controller;

import com.upstox.test.service.SubscribeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeController.class);

    @Autowired
    SubscribeServiceImpl subscribeService;

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribe(@RequestBody(required = false) String clientData) throws Exception {
        logger.info("Executing subscriber controller " + clientData);

        ResponseEntity<?> responseEntity = subscribeService.clientSubscribe(clientData);
        return responseEntity;

    }
}
