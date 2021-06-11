package com.niu.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 状态监听器
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:12]
 * @createTime [2021/06/10 16:12]
 */
@WithStateMachine()
@Slf4j
public class OrderSingleEventConfig {

    /**
     * 创建订单
     *
     * @author nza
     * @createTime 2021/6/10 16:15
     */
    @OnTransition(target = "UN_PAID")
    public void create() {
        log.info("---- 创建订单, 待支付 ----");
    }

    /**
     * 支付
     *
     * @author nza
     * @createTime 2021/6/10 16:15
     */
    @OnTransition(source = "UN_PAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        log.info("---- 用户完成支付, 待收货 ----");
    }

    /**
     * 收货
     *
     * @author nza
     * @createTime 2021/6/10 16:22
     */
    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void received() {
        log.info("---- 用户完成收货, 订单完成 ----");
    }
}
