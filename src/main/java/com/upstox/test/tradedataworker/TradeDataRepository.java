package com.upstox.test.tradedataworker;

import com.upstox.test.config.TradeDataConfig;
import com.upstox.test.model.TradeData;
import com.upstox.test.utility.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeDataRepository implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TradeDataRepository.class);

    private TradeData tradeData = new TradeData();

    private TradeDataConfig tradeDataConfig = TradeDataConfig.getTradeDataConfig_instance();


    Utility utility = new Utility();

    @Override
    public void run() {

        logger.info("Read trade data from json file");

        String jsonString = null;

        InputStream inputStream = TradeDataRepository.class.getClassLoader().getResourceAsStream("trades" + ".json");

        if (null != inputStream) {

            try (BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream))) {

                while ((jsonString = streamReader.readLine()) != null) {

                    tradeData = utility.jsonToObjTradeData(jsonString);
                    storeTradeData(tradeData);

                }

            } catch (Exception e) {
                logger.debug("Error while reading json file " + e);
            }
        }

    }

    private void storeTradeData(TradeData tradeData) {

        logger.debug("Add trade data to map list repository " + tradeData);

        List<TradeData> tradeDataList;

        if (tradeDataConfig.getTradeData().containsKey(tradeData.getSym())) {
            tradeDataList = tradeDataConfig.getTradeData().get(tradeData.getSym());
            tradeDataList.add(tradeData);
        } else {
            tradeDataList = new ArrayList<>();
            tradeDataList.add(tradeData);
        }

        tradeDataConfig.getTradeData().put(tradeData.getSym(), tradeDataList);

    }
}
