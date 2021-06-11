package com.niu.springboot.constant;

/**
 * 表单状态
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:20]
 * @createTime [2021/06/11 17:20]
 */
public enum ComplexFormStates {
    /**
     * 空白表单
     */
    BLANK_FORM,
    /**
     * 填写完表单
     */
    FULL_FORM,
    /**
     * 表单校验判断
     */
    CHECK_CHOICE,
    /**
     * 表单处理校验
     */
    DEAL_CHOICE,
    /**
     * 待处理表单
     */
    DEAL_FORM,
    /**
     * 校验完表单
     */
    CONFIRM_FORM,
    /**
     * 成功表单
     */
    SUCCESS_FORM,
    /**
     * 失败表单
     */
    FAILED_FORM;
}
