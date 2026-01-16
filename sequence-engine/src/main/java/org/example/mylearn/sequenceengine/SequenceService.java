package org.example.mylearn.sequenceengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SequenceService {
    // 使用 AtomicInteger 替代 Integer + synchronized
    private static final AtomicInteger sequenceNumber = new AtomicInteger(100);
    Logger logger = LoggerFactory.getLogger(SequenceService.class);

    public int newSequence() {
        // 直接原子自增并返回，无需手动加锁
        var id = sequenceNumber.getAndIncrement();
        logger.debug("newSequence id:{}", id);
        return id;
    }
}
