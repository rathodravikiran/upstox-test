package com.upstox.test.ohlc;

import com.upstox.test.model.Subscriber;

public interface OhlcService {
    public void computeOhlc(Subscriber subscriber) throws InterruptedException;
}
