package com.niu.springboot.config;

import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * 状态机配置类
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 18:14]
 * @createTime [2021/06/11 18:14]
 */
@Configuration
@Slf4j
@EnableStateMachine
public class StatemachineConfig {

    @Bean
    public StateMachineListener<ComplexFormStates, ComplexFormEvents> listener() {
        return new StateMachineListenerAdapter<ComplexFormStates, ComplexFormEvents>() {
            @Override
            public void stateChanged(State<ComplexFormStates, ComplexFormEvents> from, State<ComplexFormStates, ComplexFormEvents> to) {
                log.info("State change to " + to.getId());
            }
        };
    }
}
