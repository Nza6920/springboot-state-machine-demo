package com.niu.springboot.config;

import com.niu.springboot.enums.Events;
import com.niu.springboot.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.StateDoActionPolicy;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * 状态机配置
 *
 * @author [nza]
 * @version 1.0 [2021/06/07 17:17]
 * @createTime [2021/06/07 17:17]
 */
@Configuration
@EnableStateMachine
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.DRAFT, initAction())
                .state(States.DRAFT, entryAction(), exitAction())
                .state(States.PUBLISH_DONE, entryAction(), exitAction())
                .state(States.PUBLISH_DONE, context -> log.info("state action....."))
                .state(States.PUBLISH_DONE, entryAction(), exitAction())
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        // 配置状态流转逻辑
        transitions.withExternal()
                .source(States.DRAFT).target(States.PUBLISH_TODO)
                .event(Events.ONLINE)
                .guard(guard())
//                .action(Actions.errorCallingAction(action(), errorAction()))
                .action(action(), errorAction())
                .and()
                .withExternal()
                .source(States.PUBLISH_TODO).target(States.PUBLISH_DONE)
                .event(Events.PUBLISH)
                .guard(guard())
                .action(action())
                .and()
                .withExternal()
                .source(States.PUBLISH_DONE).target(States.DRAFT)
                .event(Events.ROLLBACK)
                .guard(guard())
                .action(action());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .stateDoActionPolicy(StateDoActionPolicy.IMMEDIATE_CANCEL)
//                .autoStartup(true)
                .listener(listener());
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                log.info("State change to " + to.getId());
            }
        };
    }

    @Bean
    public Guard<States, Events> guard() {
        return context -> {
            log.info("guard is running ----- event: {}, headers: {}", context.getEvent().name(), context.getMessageHeaders());
            return true;
        };
    }

    @Bean
    public Action<States, Events> action() {
        return context -> {
            log.info("action is running ----- event: {}", context.getEvent().name());
//                throw new IllegalArgumentException("出错喽");
        };
    }

    @Bean
    public Action<States, Events> initAction() {
        return context -> log.info("initAction is running");
    }

    @Bean
    public Action<States, Events> entryAction() {
        return context -> log.info("entryAction is running");
    }

    @Bean
    public Action<States, Events> exitAction() {
        return context -> log.info("exitAction is running");
    }

    @Bean
    public Action<States, Events> errorAction() {
        return context -> {
            Exception exception = context.getException();
            log.error("发生异常: {}", exception.getMessage());
        };
    }
}
