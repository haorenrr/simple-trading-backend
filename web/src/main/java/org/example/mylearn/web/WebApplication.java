package org.example.mylearn.web;

import org.example.mylearn.openapi.OpenApiController;
import org.example.mylearn.openapi.filter.ApiFilterRegistrationBean;
import org.example.mylearn.tradingengine.config.TradingEngineAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
