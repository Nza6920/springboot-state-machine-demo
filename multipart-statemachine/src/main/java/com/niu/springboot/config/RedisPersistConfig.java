package com.niu.springboot.config;

import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 15:48]
 * @createTime [2021/06/11 15:48]
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.redis", name = {"host", "port"})
@AllArgsConstructor
public class RedisPersistConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * 注入RedisStateMachinePersister对象
     */
    @Bean(name = "orderRedisPersister")
    public RedisStateMachinePersister<OrderStatesEnum, OrderEventsEnum> redisPersister() {
        return new RedisStateMachinePersister<>(redisPersist());
    }

    /**
     * 通过redisConnectionFactory创建StateMachinePersist
     */
    public StateMachinePersist<OrderStatesEnum, OrderEventsEnum, String> redisPersist() {
        RedisStateMachineContextRepository<OrderStatesEnum, OrderEventsEnum> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }
}
