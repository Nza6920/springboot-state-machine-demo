package com.niu.springboot.config.builder;

import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * 订单状态机 Builder
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:52]
 * @createTime [2021/06/10 16:52]
 */
@Component()
@Slf4j
public class OrderStateMachineBuilder {

    public static final String MACHINE_ID = "orderMachine";

    @Resource
    private Action<OrderStatesEnum, OrderEventsEnum> action;

    @Resource
    private StateMachineListener<OrderStatesEnum, OrderEventsEnum> listener;

    /**
     * 构建状态机
     *
     * @param beanFactory {@link BeanFactory}
     * @return {@link StateMachine<OrderStatesEnum, OrderEventsEnum>}
     */
    public StateMachine<OrderStatesEnum, OrderEventsEnum> build(BeanFactory beanFactory) throws Exception {

        StateMachineBuilder.Builder<OrderStatesEnum, OrderEventsEnum> builder = StateMachineBuilder.builder();

        log.info("构建订单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .listener(listener)
                .machineId(MACHINE_ID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(OrderStatesEnum.UN_PAID)
                .states(EnumSet.allOf(OrderStatesEnum.class));

        builder.configureTransitions()
                .withExternal()
                .source(OrderStatesEnum.UN_PAID)
                .target(OrderStatesEnum.WAITING_FOR_RECEIVE)
                .event(OrderEventsEnum.PAY)
                .action(action)
                .and()
                .withExternal()
                .source(OrderStatesEnum.WAITING_FOR_RECEIVE)
                .target(OrderStatesEnum.DONE)
                .event(OrderEventsEnum.RECEIVE);

        return builder.build();
    }
}
