package org.example.mylearn.sequenceengine.flowcontrol;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SentinelRuleConfig {

    @PostConstruct
    public  void init() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("rscs_sequenceController");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);

        FlowRuleManager.loadRules(List.of(flowRule));
    }
}
