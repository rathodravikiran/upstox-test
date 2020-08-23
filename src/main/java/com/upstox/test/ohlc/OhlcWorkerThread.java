package com.upstox.test.ohlc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upstox.test.model.BarChartModel;
import com.upstox.test.model.TradeData;
import com.upstox.test.tradeworker.TradeDataConfig;
import com.upstox.test.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OhlcWorkerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(OhlcWorkerThread.class);

    TradeDataConfig tradeDataConfig = TradeDataConfig.getTradeDataConfig_instance();

    private List<TradeData> tradeDataList = new ArrayList<>();
    private String tradeSymbol;
    private int tradeStartPoint = 0;
    private int barCount =0;

    @Override
    public void run() {

        logger.info("OHLC compute worker thread started at an interval of 15 seconds");

        int tradeSize;

        List<TradeData> tempList = new ArrayList<>();
        CreateBarChart createBarChart = new CreateBarChart();

        if (!tradeDataConfig.getTradeData().containsKey(tradeSymbol)) {
            tradeSize = 0;
        } else {
            tradeDataList = tradeDataConfig.getTradeData().get(tradeSymbol);
            tradeSize = tradeDataList.size();
        }

        if ((tradeSize > 0) && (tradeSize > tradeStartPoint)) {

            try {
                Collections.sort(tradeDataList);
            } catch (Exception e){
                logger.debug("incorrect data " + tradeSymbol + " " + e);
            }

            long tradeEndpointTS = tradeDataList.get(tradeStartPoint).getTS2() + 15*1000;

            int i;

            for (i = tradeStartPoint; i < tradeSize && tradeDataList.get(i).getTS2() <= tradeEndpointTS; i++) {
                tempList.add(tradeDataList.get(i));

            }

            tradeStartPoint = i;

        }

        try {
            barCount = createBarChart.setBarCharData(tempList, barCount, tradeSymbol);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        tempList.clear();
        barCount++;
        try {
            createBarChart.writeEmptyChart(barCount, tradeSymbol, Constants.INTERVAL_MSG);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

}
