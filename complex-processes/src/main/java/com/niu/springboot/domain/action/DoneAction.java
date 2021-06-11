package com.niu.springboot.domain.action;

import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 自定义结束执行器
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 18:26]
 * @createTime [2021/06/11 18:26]
 */
@Slf4j
public class DoneAction implements Action<ComplexFormStates, ComplexFormEvents> {
    @Override
    public void execute(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        log.info("流程结束, 当前ID：{}, 状态: {}", context.getStateMachine().getId(), context.getStateMachine().getState().getId());
    }
}
