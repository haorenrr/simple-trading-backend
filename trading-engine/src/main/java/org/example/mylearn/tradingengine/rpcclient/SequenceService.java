package org.example.mylearn.tradingengine.rpcclient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.mylearn.common.ErrorCode;
import org.example.mylearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {
    private final SequenceFeignClient  sequenceFeignClient;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // instead of @Autowired, inject by construction
    @Autowired
    public SequenceService(SequenceFeignClient sequenceFeignClient) {
        this.sequenceFeignClient = sequenceFeignClient;
    }

    @CircuitBreaker(name = "new-sequence", fallbackMethod = "fallback")
    public Result<Integer> newSequence(){
        return Result.ok(sequenceFeignClient.newSequence());
    }

    private Result<Integer> fallback(Throwable e){
        var msg = "in fallback(): call %s fail! fall back to fallback(), error msg: %s".formatted(this.getClass().getSimpleName(), e.getMessage());
        logger.debug(msg);
        return Result.fail(null, ErrorCode.SERVICE_UNAVAILABLE, msg);
    }
}
