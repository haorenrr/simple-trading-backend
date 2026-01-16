package org.example.mylearn.sequenceengine;

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
    public Integer newSequence() {
        return sequenceService.newSequence();
    }
}
