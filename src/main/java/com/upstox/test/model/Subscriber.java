package com.upstox.test.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Subscriber {
    private String event;
    private String symbol;
    private int interval;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "event='" + event + '\'' +
                ", symbol='" + symbol + '\'' +
                ", interval=" + interval +
                '}';
    }
}
