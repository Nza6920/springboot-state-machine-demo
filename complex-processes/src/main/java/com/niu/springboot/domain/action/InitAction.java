package com.niu.springboot.domain.action;

import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 自定义流程开始执行器
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 18:26]
 * @createTime [2021/06/11 18:26]
 */
@Slf4j
public class InitAction implements Action<ComplexFormStates, ComplexFormEvents> {
    @Override
    public void execute(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        log.info("流程开始, 当前ID：{}", context.getStateMachine().getId());
    }
}
