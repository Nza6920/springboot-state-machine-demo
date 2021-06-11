package com.niu.springboot;

import com.niu.springboot.constant.OrderEventsEnum;
import com.niu.springboot.constant.OrderStatesEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.statemachine.StateMachine;

import javax.annotation.Resource;

/**
 * 状态机测试类
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:26]
 * @createTime [2021/06/10 16:26]
 */
@Slf4j
class StatemachineTest extends BaseTest {

    @Resource
    private StateMachine<OrderStatesEnum, OrderEventsEnum> orderStatemachine;

    @Test
    void testOrder() {
        log.info("流程开始.....");
        orderStatemachine.start();
        orderStatemachine.sendEvent(OrderEventsEnum.PAY);
        orderStatemachine.sendEvent(OrderEventsEnum.RECEIVE);
        log.info("流程结束.....");
    }
}
