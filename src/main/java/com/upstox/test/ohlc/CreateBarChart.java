package com.upstox.test.ohlc;

import com.upstox.test.model.BarChartModel;
import com.upstox.test.model.TradeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class CreateBarChart {
    private static final Logger logger = LoggerFactory.getLogger(CreateBarChart.class);


    public boolean setBarCharData(List<TradeData> tempList, int barCount) {

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

        return true;
    }


}
