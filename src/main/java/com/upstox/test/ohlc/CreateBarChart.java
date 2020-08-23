package com.upstox.test.ohlc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.test.model.BarChartModel;
import com.upstox.test.model.EmptyBarEvent;
import com.upstox.test.model.TradeData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateBarChart {
    private static final Logger logger = LoggerFactory.getLogger(CreateBarChart.class);


    ObjectMapper objectMapper = new ObjectMapper();

    EmptyBarEvent emptyBarEvent = new EmptyBarEvent();

    List<BarChartModel> barChartList = new ArrayList<>();
    double quantity = 0.0;

    public void setBarCharData(List<TradeData> tempList, int barCount, String tradeSymbol) throws JsonProcessingException {

        logger.debug("Creating bar chart data");

        if (!tempList.isEmpty()) {

            writefirstBarChartRecord(tempList, barCount);

            double previousHigh = tempList.get(0).getP();
            double previousLow = tempList.get(0).getP();
            double openPrice = tempList.get(0).getP();

            for (int i = 1; i < tempList.size(); i++) {

                BarChartModel barChartModel = new BarChartModel();

                barChartModel.setO(openPrice);

                if (previousHigh < tempList.get(i).getP()) {
                    barChartModel.setH(tempList.get(i).getP());
                    previousHigh = tempList.get(i).getP();
                } else {
                    barChartModel.setH(previousHigh);
                }

                if (previousLow < tempList.get(i).getP()) {
                    barChartModel.setL(previousLow);
                } else {
                    barChartModel.setL(tempList.get(i).getP());
                    previousLow = tempList.get(i).getP();
                }

                quantity += tempList.get(i).getQ();

                barChartModel.setVolume(quantity);
                barChartModel.setSymbol(tempList.get(i).getSym());
                barChartModel.setBar_num(barCount);

                if (i == tempList.size() - 1)
                    barChartModel.setC(tempList.get(i).getP());

                barChartList.add(barChartModel);
            }

            for (BarChartModel br : barChartList)
                System.out.println(objectMapper.writeValueAsString(br));
        } else {
            logger.info("No more trade data available for " + tradeSymbol);

            emptyBarEvent.setSymbol(tradeSymbol);
            emptyBarEvent.setBar_num(barCount);

            System.out.println(objectMapper.writeValueAsString(emptyBarEvent));
        }

    }

    private void writefirstBarChartRecord(List<TradeData> tempList, int barCount) {
        logger.debug("Writing first trade data");
        BarChartModel barChartModel = new BarChartModel();

        barChartModel.setO(tempList.get(0).getP());
        barChartModel.setH(tempList.get(0).getP());
        barChartModel.setL(tempList.get(0).getP());

        quantity += tempList.get(0).getQ();

        barChartModel.setVolume(quantity);
        barChartModel.setSymbol(tempList.get(0).getSym());
        barChartModel.setBar_num(barCount);

        if (0 == tempList.size() - 1)
            barChartModel.setC(tempList.get(0).getP());

        barChartList.add(barChartModel);
    }


}
