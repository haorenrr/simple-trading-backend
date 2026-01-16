package org.example.mylearn.tradingengine.rpcclient;

import org.example.mylearn.common.ErrorCode;
import org.example.mylearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SequenceService {
    private SequenceFeignClient  sequenceFeignClient;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // instead of @Autowired, inject by construction
    public SequenceService(SequenceFeignClient sequenceFeignClient) {
        this.sequenceFeignClient = sequenceFeignClient;

    }
    public Result<Integer> newSequence(){
        try {
            return Result.ok(sequenceFeignClient.newSequence());
        }catch (Exception e){
            logger.warn("call {} fail! msg: {}", this.getClass().getSimpleName(), e.getMessage());
            return Result.fail(null, ErrorCode.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }
}
