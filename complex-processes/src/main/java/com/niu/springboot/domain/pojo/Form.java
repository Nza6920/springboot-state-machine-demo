package com.niu.springboot.domain.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表单实体
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:28]
 * @createTime [2021/06/11 17:28]
 */
@Data
@Accessors(chain = true)
public class Form {

    private String id;

    private String formName;

    private String formTxt;

    /**
     * 0: 未超时 1: 超时
     */
    private Integer status;
}
