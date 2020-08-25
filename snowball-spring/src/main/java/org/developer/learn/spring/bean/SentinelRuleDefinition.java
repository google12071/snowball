package org.developer.learn.spring.bean;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.developer.learn.spring.constant.ResourceNameDef;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentinelRuleDefinition
 * @Description: 流控规则配置
 * @Author lfq
 * @Date 2020/8/25
 **/
@Component
@Slf4j
public class SentinelRuleDefinition {

    @PostConstruct
    public void loadRules() {
        initDegradeRule();
        initFlowRule();
        log.info("sentinel规则初始化成功...");
    }

    /**
     * 降级规则
     */
    private void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(ResourceNameDef.GET_USER_BY_UID);
        // 异常率超过0.1，就认为服务不可用，执行熔断操作
        rule.setCount(0.1);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
        rule.setTimeWindow(10);
        rule.setMinRequestAmount(20);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
        log.info("sentinel 熔断规则定义完毕！");
    }

    /**
     * 流控规则
     */
    private void initFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(ResourceNameDef.GET_USER_BY_UID);
        // qps上限为1：
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        log.info("sentinel 限流规则定义完毕！");
    }
}
