package com.niu.springboot.domain.guard;

import cn.hutool.core.util.StrUtil;
import com.niu.springboot.constant.ComplexFormEvents;
import com.niu.springboot.constant.ComplexFormStates;
import com.niu.springboot.domain.pojo.Form;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * 流程守卫
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:28]
 * @createTime [2021/06/11 17:28]
 */
public class ComplexFormCheckChoiceGuard implements Guard<ComplexFormStates, ComplexFormEvents> {
    @Override
    public boolean evaluate(StateContext<ComplexFormStates, ComplexFormEvents> context) {
        Form form = context.getMessage().getHeaders().get("form", Form.class);
        return form != null && StrUtil.isNotEmpty(form.getFormName());
    }
}
