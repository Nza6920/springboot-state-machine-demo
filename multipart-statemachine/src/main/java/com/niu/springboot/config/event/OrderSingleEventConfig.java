package com.niu.springboot.config.event;

import com.niu.springboot.constant.enums.OrderEventsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import static com.niu.springboot.config.builder.OrderStateMachineBuilder.MACHINE_ID;

/**
 * 状态监听器
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:12]
 * @createTime [2021/06/10 16:12]
 */
@WithStateMachine(id = MACHINE_ID)
@Slf4j
public class OrderSingleEventConfig {

    /**
     * 创建订单
     *
     * @author nza
     * @createTime 2021/6/10 16:15
     */
    @OnTransition(target = "UN_PAID")
    public void create(Message<OrderEventsEnum> message) {
        log.info("---- 创建订单, 待支付 ----");
        log.info("message: {}", message);
    }

    /**
     * 支付
     *
     * @author nza
     * @createTime 2021/6/10 16:15
     */
    @OnTransition(source = "UN_PAID", target = "WAITING_FOR_RECEIVE")
    public void pay(Message<OrderEventsEnum> message) {
        log.info("---- 用户完成支付, 待收货 ----");
        log.info("message: {}", message);
    }

    /**
     * 收货
     *
     * @author nza
     * @createTime 2021/6/10 16:22
     */
    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void received(Message<OrderEventsEnum> message) {
        log.info("---- 用户完成收货, 订单完成 ----");
        log.info("message: {}", message);
    }
}
