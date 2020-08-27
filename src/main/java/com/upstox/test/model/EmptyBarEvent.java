package com.upstox.test.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmptyBarEvent {
    private final String event = "ohlc_notify";
    private String symbol;
    private int bar_num;

}
