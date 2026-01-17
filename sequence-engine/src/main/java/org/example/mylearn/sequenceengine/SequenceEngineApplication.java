package org.example.mylearn.sequenceengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        //for Sentinel's version problem, we have to exclude it's interact with acutator..
        exclude = {com.alibaba.cloud.sentinel.endpoint.SentinelEndpointAutoConfiguration.class}
)
public class SequenceEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SequenceEngineApplication.class, args);
    }

}
