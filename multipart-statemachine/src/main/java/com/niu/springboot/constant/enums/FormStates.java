package com.niu.springboot.constant.enums;

/**
 * 表单状态
 *
 * @version 1.0 [2021/06/11 11:22]
 * @author [nza]
 * @createTime [2021/06/11 11:22]
 */
public enum FormStates {
    /**
     * 空白表单
     */
    BLANK_FORM,
    /**
     * 填写完表单
     */
    FULL_FORM,
    /**
     * 校验表单
     */
    CONFIRM_FORM,
    /**
     * 成功表单
     */
    SUCCESS_FORM;
}
