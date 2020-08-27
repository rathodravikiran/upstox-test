package com.upstox.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class TradeData implements Comparable<TradeData> {

    private String sym;
    @JsonIgnoreProperties
    private String T;
    private double P;
    private Double Q;
    @JsonIgnoreProperties
    private Timestamp TS;
    private String side;
    private Long TS2;

    @Override
    public int compareTo(TradeData tradeData) {
        return (int) (this.TS2 - tradeData.getTS2());
    }
}
