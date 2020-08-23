package com.upstox.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.test.model.SubscribeResponse;
import com.upstox.test.model.Subscriber;
import com.upstox.test.ohlc.OhlcServiceImpl;
import com.upstox.test.tradeworker.ClientDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Component
public class SubscribeServiceImpl implements SubscribeService {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeServiceImpl.class);

    @Autowired
    SubscribeResponse response;

    @Autowired
    OhlcServiceImpl ohlcService;

    ClientDataConfig clientDataConfig = ClientDataConfig.getClientDataConfig_Instance();

    ResponseEntity<SubscribeResponse> responseEntity = null;

    @Override
    public ResponseEntity<?> clientSubscribe(String subscribeData) throws IOException {

        logger.info("Client subscribing for trade " + subscribeData);

        try {
            Subscriber subscriber = new ObjectMapper().readValue(subscribeData, Subscriber.class);

            if ((!subscriber.getSymbol().isEmpty()) && (!subscriber.getEvent().isEmpty())) {

                addToClientList(subscriber);

            } else {
                response.setMessage("Unable to subscribe, Invalid input");
                response.setStatus(HttpStatus.BAD_REQUEST);
                logger.info("Unable to subscribe client, Invalid input => " + subscribeData);
            }

            generateResponse(subscriber);

            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            logger.info("Unable to subscribe client, Invalid input => " + e);
            response.setSymbol(null);
            response.setEvent(null);
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Unable to subscribe, Invalid input");
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;

    }

    private void addToClientList(Subscriber subscriber) throws InterruptedException {
        if (clientDataConfig.getClientList().add(subscriber)) {
            response.setMessage("Client Subscribed successfully for trade..!" + subscriber.getSymbol());
            response.setStatus(HttpStatus.OK);
            logger.info("Client subscribed for trade " + subscriber.getSymbol());

            new Thread(() -> {
                try {
                    ohlcService.computeOhlc(subscriber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();


        } else {
            response.setMessage("Unable to Subscribe, Client already Subscribed for trade...!" + subscriber.getSymbol());
            response.setStatus(HttpStatus.BAD_REQUEST);
            logger.info("Duplicate subscription " + subscriber.getSymbol());
        }
    }

    private void generateResponse(Subscriber subscriber) {
        response.setEvent(subscriber.getEvent());
        response.setSymbol(subscriber.getSymbol());
    }

}
