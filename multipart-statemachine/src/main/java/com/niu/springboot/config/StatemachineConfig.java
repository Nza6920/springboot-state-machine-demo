package com.niu.springboot.config;

import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

/**
 * 状态机配置类
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:01]
 * @createTime [2021/06/10 16:01]
 */
@Configuration
@Slf4j
@EnableStateMachine
public class StatemachineConfig {

    @Bean
    public Action<OrderStatesEnum, OrderEventsEnum> action() {
        return context -> log.info("执行action, 当前ID：{}", context.getStateMachine().getId());
    }

    @Bean
    public StateMachineListener<OrderStatesEnum, OrderEventsEnum> listener() {
        return new StateMachineListenerAdapter<OrderStatesEnum, OrderEventsEnum>() {
            @Override
            public void stateChanged(State<OrderStatesEnum, OrderEventsEnum> from, State<OrderStatesEnum, OrderEventsEnum> to) {
                log.info("State change to " + to.getId());
            }
        };
    }
}
