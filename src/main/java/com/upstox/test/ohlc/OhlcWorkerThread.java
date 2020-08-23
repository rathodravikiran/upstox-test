package com.upstox.test.ohlc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upstox.test.model.BarChartModel;
import com.upstox.test.model.TradeData;
import com.upstox.test.tradeworker.TradeDataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OhlcWorkerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(OhlcWorkerThread.class);

    TradeDataConfig tradeDataConfig = TradeDataConfig.getTradeDataConfig_instance();

    private List<TradeData> tradeDataList = null;
    private String tradeSymbol;
    private int tradeStartPoint = 0;
    private int barCount;

    @Override
    public void run() {

        logger.info("OHLC compute thread started at interval of 15 seconds");

        tradeDataList = tradeDataConfig.getTradeData().get(tradeSymbol);
        int tradeSize = tradeDataList.size();
        List<TradeData> tempList = new ArrayList<>();
        CreateBarChart createBarChart = new CreateBarChart();

        barCount++;

        if ((tradeSize > 0) && (tradeSize > tradeStartPoint)) {

            Collections.sort(tradeDataList);
            long tradeEndpointTS = tradeDataList.get(tradeStartPoint).getTS2() + 15 * 1000;

            int i;

            for (i = tradeStartPoint; i < tradeSize && tradeDataList.get(i).getTS2() <= tradeEndpointTS; i++) {
                tempList.add(tradeDataList.get(i));

            }

            tradeStartPoint = i;

        }
        try {
            createBarChart.setBarCharData(tempList, barCount, tradeSymbol);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        tempList.clear();
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

}
