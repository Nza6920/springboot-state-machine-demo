package com.niu.springboot.config;

import com.niu.springboot.constant.OrderEventsEnum;
import com.niu.springboot.constant.OrderStatesEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置类
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:01]
 * @createTime [2021/06/10 16:01]
 */
@Configuration
@EnableStateMachine
@Slf4j
public class StatemachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatesEnum, OrderEventsEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatesEnum, OrderEventsEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStatesEnum.UN_PAID)
                .states(EnumSet.allOf(OrderStatesEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatesEnum, OrderEventsEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatesEnum.UN_PAID)
                .target(OrderStatesEnum.WAITING_FOR_RECEIVE)
                .event(OrderEventsEnum.PAY)
                .and()
                .withExternal()
                .source(OrderStatesEnum.WAITING_FOR_RECEIVE)
                .target(OrderStatesEnum.DONE)
                .event(OrderEventsEnum.RECEIVE);
    }
}
