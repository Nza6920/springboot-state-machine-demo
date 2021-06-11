package com.niu.springboot.config.persist;

import com.google.common.collect.Maps;
import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 内存持久化存储
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 15:21]
 * @createTime [2021/06/11 15:21]
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<OrderStatesEnum, OrderEventsEnum, String> {

    private final Map<String, StateMachineContext<OrderStatesEnum, OrderEventsEnum>> MACHINE_BUCKET = Maps.newHashMap();

    @Override
    public void write(StateMachineContext<OrderStatesEnum, OrderEventsEnum> context, String key) throws Exception {
        MACHINE_BUCKET.put(key, context);
    }

    @Override
    public StateMachineContext<OrderStatesEnum, OrderEventsEnum> read(String key) throws Exception {
        return MACHINE_BUCKET.get(key);
    }
}
