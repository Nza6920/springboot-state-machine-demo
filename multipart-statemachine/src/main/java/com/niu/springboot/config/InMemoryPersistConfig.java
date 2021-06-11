package com.niu.springboot.config;

import com.niu.springboot.config.persist.InMemoryStateMachinePersist;
import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * 内存持久化存储配置
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 15:50]
 * @createTime [2021/06/11 15:50]
 */
@Configuration
@ConditionalOnMissingBean(RedisPersistConfig.class)
@ConditionalOnBean(InMemoryStateMachinePersist.class)
public class InMemoryPersistConfig {
    @Bean(name = "orderMemoryPersister")
    public StateMachinePersister<OrderStatesEnum, OrderEventsEnum, String> getPersister(InMemoryStateMachinePersist inMemoryStateMachinePersist) {
        return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
    }
}
