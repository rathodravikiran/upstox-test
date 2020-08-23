package com.upstox.test.config;

import com.upstox.test.model.TradeData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TradeDataConfig {

    private Map<String, List<TradeData>> tradeData;

    private static TradeDataConfig TradeDataConfig_instance = null;

    public Map<String, List<TradeData>> getTradeData() {
        return tradeData;
    }

    private TradeDataConfig(){
        tradeData = new HashMap<>();
    }

    public static TradeDataConfig getTradeDataConfig_instance() {
        if (TradeDataConfig_instance == null)
            TradeDataConfig_instance = new TradeDataConfig();
        return TradeDataConfig_instance;

    }
}
