package com.niu.springboot.config.event;

import com.niu.springboot.config.builder.ComplexFormStateMachineBuilder;
import com.niu.springboot.constant.ComplexFormEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 事件监听配置
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:53]
 * @createTime [2021/06/11 17:53]
 */
@WithStateMachine(id = ComplexFormStateMachineBuilder.MACHINE_ID)
@Slf4j
public class ComplexFormEventConfig {

    /**
     * 当前状态BLANK_FORM
     */
    @OnTransition(target = "BLANK_FORM")
    public void create() {
        log.info("--- 空白复杂表单 ---");
    }

    /**
     * 填写完复杂表单
     */
    @OnTransition(source = "BLANK_FORM", target = "FULL_FORM")
    public void write(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 填写完复杂表单 ---");
    }

    /**
     * 校验复杂表单
     */
    @OnTransition(source = "FULL_FORM", target = "CHECK_CHOICE")
    public void check(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 校验复杂表单 ---");
    }

    /**
     * 不会执行
     */
    @OnTransition(source = "CHECK_CHOICE", target = "CONFIRM_FORM")
    public void check2confirm(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 校验表单到待提交表单(choice true) ---");
    }

    /**
     * 不会执行
     */
    @OnTransition(source = "CHECK_CHOICE", target = "DEAL_FORM")
    public void check2deal(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 校验表单到待提交表单(choice false) ---");
    }

    @OnTransition(source = "DEAL_FORM", target = "DEAL_CHOICE")
    public void deal(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 处理复杂表单 ---");
    }

    /**
     * 不会执行
     */
    @OnTransition(source = "DEAL_CHOICE", target = "FAILED_FORM")
    public void deal2fail(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 处理复杂表单失败(choice false) ---");
    }

    /**
     * 不会执行
     */
    @OnTransition(source = "DEAL_CHOICE", target = "FULL_FORM")
    public void deal2full(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 处理复杂表单到重新填写表单(choice true) ---");
    }


    /**
     * CONFIRM_FORM->SUCCESS_FORM 执行的动作
     */
    @OnTransition(source = "CONFIRM_FORM", target = "SUCCESS_FORM")
    public void submit(Message<ComplexFormEvents> message) {
        log.info("传递的参数：{}", message);
        log.info("--- 表单提交成功 ---");
    }
}
