package com.upstox.test.tradeworker;

import com.upstox.test.model.TradeData;
import com.upstox.test.utility.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(TradeDataRepository.class);

    @Autowired
    private TradeData tradeData;

    private TradeDataConfig tradeDataConfig = TradeDataConfig.getTradeDataConfig_instance();

    @Autowired
    Utility utility;

    @PostConstruct
    public void readJson() throws IOException {

        logger.info("Read trade data from json");

        String jsonString = null;

        InputStream inputStream = TradeDataRepository.class.getClassLoader().getResourceAsStream("trades" + ".json");

        if (null != inputStream) {

            try (BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream))) {

                while ((jsonString = streamReader.readLine()) != null) {

                    tradeData = utility.jsonToObjTradeData(jsonString);
                    storeTradeData(tradeData);

                }

            } catch (Exception e) {
                System.out.println("error reading file " + e);
            }
        }

    }

    private void storeTradeData(TradeData tradeData) {

        logger.info("Add trade data to map list repository " + tradeData);

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
