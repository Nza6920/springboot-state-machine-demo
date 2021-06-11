package com.niu.springboot.config.builder;

import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import com.niu.springboot.domain.action.DoneAction;
import com.niu.springboot.domain.action.InitAction;
import com.niu.springboot.domain.guard.ComplexFormCheckChoiceGuard;
import com.niu.springboot.domain.guard.ComplexFormDealChoiceGuard;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * 复杂表单构造器
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:24]
 * @createTime [2021/06/11 17:24]
 */
@Component
@Slf4j
@AllArgsConstructor
public class ComplexFormStateMachineBuilder {

    public static final String MACHINE_ID = "complexFormMachine";

    private final StateMachineListener<ComplexFormStates, ComplexFormEvents> listener;


    /**
     * 构建状态机
     */
    public StateMachine<ComplexFormStates, ComplexFormEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ComplexFormStates, ComplexFormEvents> builder = StateMachineBuilder.builder();

        log.info("构建复杂表单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINE_ID)
                .listener(listener)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(ComplexFormStates.BLANK_FORM, new InitAction())
                .choice(ComplexFormStates.CHECK_CHOICE)
                .choice(ComplexFormStates.DEAL_CHOICE)
                .state(ComplexFormStates.FAILED_FORM, new DoneAction())
                .states(EnumSet.allOf(ComplexFormStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(ComplexFormStates.BLANK_FORM).target(ComplexFormStates.FULL_FORM)
                .event(ComplexFormEvents.WRITE)
                .and()
                .withExternal()
                .source(ComplexFormStates.FULL_FORM).target(ComplexFormStates.CHECK_CHOICE)
                .event(ComplexFormEvents.CHECK)
                .and()
                .withChoice()
                .source(ComplexFormStates.CHECK_CHOICE)
                .first(ComplexFormStates.CONFIRM_FORM, new ComplexFormCheckChoiceGuard())
                .last(ComplexFormStates.DEAL_FORM)
                .and()
                .withExternal()
                .source(ComplexFormStates.CONFIRM_FORM).target(ComplexFormStates.SUCCESS_FORM)
                .event(ComplexFormEvents.SUBMIT)
                .and()
                .withExternal()
                .source(ComplexFormStates.DEAL_FORM).target(ComplexFormStates.DEAL_CHOICE)
                .event(ComplexFormEvents.DEAL)
                .and()
                .withChoice()
                .source(ComplexFormStates.DEAL_CHOICE)
                .first(ComplexFormStates.FULL_FORM, new ComplexFormDealChoiceGuard())
                .last(ComplexFormStates.FAILED_FORM);

        return builder.build();
    }
}
