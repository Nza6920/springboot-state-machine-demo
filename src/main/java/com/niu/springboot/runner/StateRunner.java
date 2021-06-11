package com.niu.springboot.runner;

import com.niu.springboot.enums.Events;
import com.niu.springboot.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2021/06/08 10:41]
 * @createTime [2021/06/08 10:41]
 */
@Component
@Slf4j
public class StateRunner implements CommandLineRunner {

    @Autowired
    private StateMachine<States, Events> statesEventsStateMachine;

    @Override
    public void run(String... args) {
        statesEventsStateMachine.start();
        statesEventsStateMachine.sendEvent(Events.ONLINE);
        statesEventsStateMachine.sendEvent(Events.PUBLISH);
        statesEventsStateMachine.sendEvent(Events.ROLLBACK);
        log.info("第一次执行完毕");

        statesEventsStateMachine.start();
        statesEventsStateMachine.sendEvent(Events.ONLINE);
        statesEventsStateMachine.sendEvent(Events.PUBLISH);
        statesEventsStateMachine.sendEvent(Events.ROLLBACK);
        log.info("第二次执行完毕");
    }
}
