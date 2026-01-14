package org.example.mylearn.sequenceengine;

import org.example.mylearn.common.rpc.SequenceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/seq")
class SequenceController implements SequenceApi {

    @Autowired
    private SequenceService sequenceService;

    @Override
    public Integer newSequence() {
        return sequenceService.newSequence();
    }
}
