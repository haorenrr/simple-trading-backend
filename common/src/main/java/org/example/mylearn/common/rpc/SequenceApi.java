package org.example.mylearn.common.rpc;

import org.springframework.web.bind.annotation.GetMapping;

public interface SequenceApi {

    @GetMapping("/seq/next")
    Integer newSequence();
}
