package org.example.mylearn.sequenceengine;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SequenceService {

    // 使用 AtomicInteger 替代 Integer + synchronized
    private static final AtomicInteger sequenceNumber = new AtomicInteger(100);

    public int newSequence() {
        // 直接原子自增并返回，无需手动加锁
        return sequenceNumber.getAndIncrement();
    }
}
