package com.upstox.test.ohlc;

import com.upstox.test.model.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class OhlcServiceImpl implements OhlcService {

    private static final Logger logger = LoggerFactory.getLogger(OhlcServiceImpl.class);

    @Override
    public void computeOhlc(Subscriber subscriber) throws InterruptedException {

        logger.info("OHLC computing started for trade " + subscriber.getSymbol() + " " + "At interval " + subscriber.getInterval());

        OhlcWorkerThread ohlcWorkerThread = new OhlcWorkerThread();
        ohlcWorkerThread.setTradeSymbol(subscriber.getSymbol());

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(ohlcWorkerThread, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);
        scheduledExecutorService.shutdown();
    }
}
