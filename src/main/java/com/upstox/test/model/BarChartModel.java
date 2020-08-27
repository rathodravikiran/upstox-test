package com.upstox.test.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BarChartModel {
    private double o;           //Open trade price
    private double h;           //High trade price
    private double l;           //Low trade price
    private double c;           //Close trade price
    private double volume;      //Accumulative quantity
    private final String event = "ohlc_notify";
    private String symbol;       // Trade symbol
    private int bar_num;

}
