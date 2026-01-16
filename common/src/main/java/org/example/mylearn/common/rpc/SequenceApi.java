package org.example.mylearn.common.rpc;

import org.springframework.web.bind.annotation.GetMapping;

public interface SequenceApi {

    String SERVER_NAME = "sequence-engine";

    @GetMapping("/seq/next")
    Integer newSequence();
}
