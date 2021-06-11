package com.niu.springboot.domain.guard;

import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import com.niu.springboot.domain.pojo.Form;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * 表单守卫
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:39]
 * @createTime [2021/06/11 17:39]
 */
public class ComplexFormDealChoiceGuard implements Guard<ComplexFormStates, ComplexFormEvents> {
    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        Form form = (Form) context.getMessageHeader("form");
        return form != null && form.getStatus() == 0;
    }
}
