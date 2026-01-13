package org.example.mylearn.openapi;

import org.example.mylearn.sequenceengine.SequenceService;
import org.example.mylearn.tradingengine.asset.AssetService;
import org.example.mylearn.tradingengine.clearing.ClearingServiceImpl;
import org.example.mylearn.tradingengine.config.TradingEngineAutoConfiguration;
import org.example.mylearn.tradingengine.engine.TradingService;
import org.example.mylearn.tradingengine.engine.TradingServiceImpl;
import org.example.mylearn.tradingengine.match.MatcherServiceImpl;
import org.example.mylearn.tradingengine.order.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({TradingEngineAutoConfiguration.class})
public class OpenapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
    }

}
