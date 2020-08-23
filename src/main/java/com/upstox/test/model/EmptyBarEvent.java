package com.upstox.test.model;

import org.springframework.stereotype.Component;

@Component
public class EmptyBarEvent {
    private final String event = "ohlc_notify";
    private String symbol;
    private int bar_num;

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
        return "EmptyBarEvent{" +
                "event='" + event + '\'' +
                ", symbol='" + symbol + '\'' +
                ", bar_num=" + bar_num +
                '}';
    }
}
