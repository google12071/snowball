package org.developer.learn.spring.bean;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SentinelConfig
 * @Description:Sentinel流控配置
 * @Author lfq
 * @Date 2020/8/25
 **/
@Configuration
@Slf4j
public class SentinelConfig {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        log.info("sentinelResourceAspect 装载成功");
        return new SentinelResourceAspect();
    }
}

