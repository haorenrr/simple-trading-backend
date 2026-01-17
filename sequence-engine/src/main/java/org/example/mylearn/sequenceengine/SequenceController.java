package org.example.mylearn.sequenceengine;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.mylearn.common.rpc.SequenceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/seq")
class SequenceController implements SequenceApi {

    private final SequenceService sequenceService;

    @Autowired
    SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @Override
    @SentinelResource(value = "rscs_sequenceController")
    public Integer newSequence() {
        return sequenceService.newSequence();
    }
}
