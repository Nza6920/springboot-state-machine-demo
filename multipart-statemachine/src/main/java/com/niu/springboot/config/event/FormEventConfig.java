package com.niu.springboot.config.event;

import com.niu.springboot.config.builder.FormStateMachineBuilder;
import com.niu.springboot.constant.enums.FormEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Form 流程监听器
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 11:39]
 * @createTime [2021/06/11 11:39]
 */
@WithStateMachine(id = FormStateMachineBuilder.MACHINE_ID)
@Slf4j
public class FormEventConfig {

    @OnTransition(target = "BLANK_FORM")
    public void create(Message<FormEvents> message) {
        log.info("---- 空白表单 ----");
        log.info("message: {}", message);
    }

    @OnTransition(source = "BLANK_FORM", target = "FULL_FORM")
    public void write(Message<FormEvents> message) {
        log.info("---- 填写表单 ----");
        log.info("message: {}", message);
    }

    @OnTransition(source = "FULL_FORM", target = "CONFIRM_FORM")
    public void confirm(Message<FormEvents> message) {
        log.info("---- 校验表单 ----");
        log.info("message: {}", message);
    }

    @OnTransition(source = "CONFIRM_FORM", target = "SUCCESS_FORM")
    public void submit(Message<FormEvents> message) {
        log.info("---- 表单提交成功 ----");
        log.info("message: {}", message);
    }
}
