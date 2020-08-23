package com.upstox.test.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.test.model.TradeData;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Utility {

    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    @Autowired
    TradeData tradeData;

    public <T> T jsonToObj(String json, Class<T> convertToObject) throws IOException {
        logger.info("json to object " + json);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(json, convertToObject);
    }

    public TradeData jsonToObjTradeData(String json) throws IOException, JSONException {

        logger.info("Convert Json To Trade Data model");

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        tradeData = mapper.readValue(json, TradeData.class);

        JSONObject jsonObject = new JSONObject(json);

        tradeData.setP((jsonObject.getDouble("P")));
        tradeData.setQ((jsonObject.getDouble("Q")));
        tradeData.setTS2((Long) jsonObject.get("TS2"));

        return tradeData;
    }
}
