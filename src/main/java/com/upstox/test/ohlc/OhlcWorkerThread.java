package com.upstox.test.ohlc;

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

        logger.info("OHLC compute thread started at interval 15");

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

//            setBarCharData(tempList);



        }
        createBarChart.setBarCharData(tempList, barCount, tradeSymbol);

        tempList.clear();
    }

    public void setTradeSymbol(String tradeSymbol) {
        this.tradeSymbol = tradeSymbol;
    }

    private void setBarCharData(List<TradeData> tempList) {

        logger.info("creating bar chart data");

        List<BarChartModel> barChartList = new ArrayList<>();
        double quantity = 0.0;

        for (int i = 0; i < tempList.size(); i++) {

            BarChartModel barChartModel = new BarChartModel();

            barChartModel.setO(tempList.get(i).getP());
            barChartModel.setH(tempList.get(i).getP());
            barChartModel.setL(tempList.get(i).getP());
            barChartModel.setC(tempList.get(i).getP());
            quantity += tempList.get(i).getQ();
            barChartModel.setVolume(quantity);
            barChartModel.setSymbol(tempList.get(i).getSym());
            barChartModel.setBar_num(barCount);

            barChartList.add(barChartModel);
        }
        for (BarChartModel br : barChartList)
            System.out.println(br);

    }

}
