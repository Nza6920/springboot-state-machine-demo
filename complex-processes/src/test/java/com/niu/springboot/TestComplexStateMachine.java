package com.niu.springboot;

import com.niu.springboot.config.builder.ComplexFormStateMachineBuilder;
import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import com.niu.springboot.domain.pojo.Form;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

/**
 * 测试类
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 18:00]
 * @createTime [2021/06/11 18:00]
 */
@Slf4j
class TestComplexStateMachine extends BaseTest {

    @Autowired
    private ComplexFormStateMachineBuilder complexFormStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;

    @Test
    void testFailedForm() throws Exception {

        StateMachine<ComplexFormStates, ComplexFormEvents> stateMachine = complexFormStateMachineBuilder.build(beanFactory);
        System.out.println(stateMachine.getId());

        log.info("启动流程");
        Form form1 = new Form();
        form1.setId("form-1")
                .setFormName("表单1")
                .setFormTxt("内容1内容1内容1内容1内容1内容1")
                .setStatus(0);
        Message<ComplexFormEvents> message = MessageBuilder.withPayload(ComplexFormEvents.WRITE).setHeader("form", form1).build();
        stateMachine.start();
        stateMachine.sendEvent(message);

        form1.setFormName(null);
        message = MessageBuilder.withPayload(ComplexFormEvents.CHECK).setHeader("form", form1).build();
        stateMachine.sendEvent(message);

        form1.setStatus(1);
        message = MessageBuilder.withPayload(ComplexFormEvents.DEAL).setHeader("form", form1).build();
        stateMachine.sendEvent(message);
        log.info("流程结束");
    }
}
