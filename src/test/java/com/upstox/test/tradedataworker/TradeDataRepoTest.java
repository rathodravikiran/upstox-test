package com.upstox.test.tradedataworker;

import com.upstox.test.config.TradeDataConfig;
import com.upstox.test.config.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
@Category(UnitTests.class)
public class TradeDataRepoTest {
    @InjectMocks
    TradeDataRepository tradeDataRepository;

    TradeDataConfig tradeDataConfig = TradeDataConfig.getTradeDataConfig_instance();

    @Test
    public void runTest(){
        new Thread(tradeDataRepository).start();
        assertTrue(tradeDataConfig.getTradeData().isEmpty());

    }
}
