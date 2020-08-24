package com.upstox.test.model;

import org.springframework.stereotype.Component;

@Component
public class BarChartModel {
    private double o;           //Open trade price
    private double h;           //High trade price
    private double l;           //Low trade price
    private double c;           //Close trade price
    private double volume;      //Accumulative quantity
    private final String event = "ohlc_notify";
    private String symbol;       // Trade symbol
    private int bar_num;

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getEvent() {
        return event;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getBar_num() {
        return bar_num;
    }

    public void setBar_num(int bar_num) {
        this.bar_num = bar_num;
    }

    @Override
    public String toString() {
        return "BarChartModel{" +
                "o=" + o +
                ", h=" + h +
                ", l=" + l +
                ", c=" + c +
                ", volume=" + volume +
                ", event='" + event + '\'' +
                ", symbol='" + symbol + '\'' +
                ", bar_num=" + bar_num +
                '}';
    }
}
