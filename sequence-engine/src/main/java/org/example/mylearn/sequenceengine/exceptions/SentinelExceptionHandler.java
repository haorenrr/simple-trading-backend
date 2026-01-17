package org.example.mylearn.sequenceengine.exceptions;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.example.mylearn.common.ErrorCode;
import org.example.mylearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SentinelExceptionHandler {

    Logger logger = LoggerFactory.getLogger(SentinelExceptionHandler.class);

    @ExceptionHandler(FlowException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public Result<Void> handleFlowException(Exception e){
        logger.debug("blocked by sentinal, {}", e.getMessage());
        return Result.fail(null, ErrorCode.FLOW_CONTROL, ErrorCode.FLOW_CONTROL.getMessage());
    }
}
