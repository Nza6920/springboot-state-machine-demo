package com.niu.springboot;

import com.niu.springboot.config.builder.FormStateMachineBuilder;
import com.niu.springboot.config.builder.OrderStateMachineBuilder;
import com.niu.springboot.constant.enums.FormEvents;
import com.niu.springboot.constant.enums.FormStates;
import com.niu.springboot.constant.enums.OrderEventsEnum;
import com.niu.springboot.constant.enums.OrderStatesEnum;
import com.niu.springboot.domain.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.Map;

/**
 * 测试多实例状态机
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 17:04]
 * @createTime [2021/06/10 17:04]
 */
@Slf4j
class MultipartStateMachineTest extends BaseTest {

    @Autowired
    private OrderStateMachineBuilder orderStateMachineBuilder;

    @Autowired
    private FormStateMachineBuilder formStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired()
    private StateMachinePersister<OrderStatesEnum, OrderEventsEnum, String> stateMachinePersister;

    @Autowired
    private Map<String, StateMachinePersister<OrderStatesEnum, OrderEventsEnum, String>> persisterContainer;

    @Test
    void testOrderMachine() throws Exception {

        StateMachine<OrderStatesEnum, OrderEventsEnum> stateMachine = orderStateMachineBuilder
                .build(beanFactory);

        // 创建流程
        stateMachine.start();


        // 触发PAY事件
        stateMachine.sendEvent(OrderEventsEnum.PAY);

        // 触发RECEIVE事件
        Order order = new Order("1", "订单1", "北京市昌平区");
        Message<OrderEventsEnum> message = MessageBuilder.withPayload(OrderEventsEnum.RECEIVE).setHeader("order", order).build();
        stateMachine.sendEvent(message);

        log.info("最终状态：{}", stateMachine.getState().getId());
    }

    @Test
    void testFormMachine() throws Exception {

        StateMachine<FormStates, FormEvents> stateMachine = formStateMachineBuilder.build(beanFactory);

        // 创建流程
        stateMachine.start();

        stateMachine.sendEvent(FormEvents.WRITE);

        stateMachine.sendEvent(FormEvents.CONFIRM);

        stateMachine.sendEvent(FormEvents.SUBMIT);

        log.info("最终状态：{}", stateMachine.getState().getId());
    }

    @Test
    void testInmemoryPersister() throws Exception {
        StateMachine<OrderStatesEnum, OrderEventsEnum> stateMachine = orderStateMachineBuilder.build(beanFactory);

        // 启动流程
        stateMachine.start();

        //发送PAY事件
        Order order = new Order();
        order.setId("1");
        Message<OrderEventsEnum> payMessage = MessageBuilder.withPayload(OrderEventsEnum.PAY).setHeader("order", order).build();
        stateMachine.sendEvent(payMessage);
        //持久化stateMachine
        stateMachinePersister.persist(stateMachine, order.getId());
        log.info("---- 持久化完成 ----");
    }

    @Test
    void testRedisPersister() throws Exception {
        StateMachine<OrderStatesEnum, OrderEventsEnum> stateMachine = orderStateMachineBuilder.build(beanFactory);

        // 启动流程
        stateMachine.start();

        //发送PAY事件
        Order order = new Order();
        order.setId("order-1");
        Message<OrderEventsEnum> payMessage = MessageBuilder.withPayload(OrderEventsEnum.PAY).setHeader("order", order).build();
        stateMachine.sendEvent(payMessage);
        //持久化stateMachine
        stateMachinePersister.persist(stateMachine, order.getId());
        log.info("---- 持久化完成 ----");
    }

    @Test
    void restoreStateMachine() throws Exception {
        StateMachine<OrderStatesEnum, OrderEventsEnum> stateMachine = orderStateMachineBuilder.build(beanFactory);
        stateMachinePersister.restore(stateMachine, "order-1");
        log.info("恢复状态机后的状态为：{}", stateMachine.getState().getId());
    }
}
