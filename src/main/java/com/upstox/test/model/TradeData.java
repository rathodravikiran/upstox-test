package com.upstox.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
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

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public double getQ() {
        return Q;
    }

    public void setQ(Double q) {
        Q = q;
    }

    public Timestamp getTS() {
        return TS;
    }

    public void setTS(Timestamp TS) {
        this.TS = TS;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Long getTS2() {
        return TS2;
    }

    public void setTS2(Long TS2) {
        this.TS2 = TS2;
    }

    @Override
    public String toString() {
        return "TradeData{" +
                "sym='" + sym + '\'' +
                ", T='" + T + '\'' +
                ", P=" + P +
                ", Q=" + Q +
                ", TS=" + TS +
                ", side='" + side + '\'' +
                ", TS2=" + TS2 +
                '}';
    }

    @Override
    public int compareTo(TradeData tradeData) {
        return (int) (this.TS2 - tradeData.getTS2());
    }
}
