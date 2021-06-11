package com.niu.springboot.constant;

/**
 * 订单状态枚举
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 16:03]
 * @createTime [2021/06/10 16:03]
 */
public enum OrderStatesEnum {

    /**
     * 未支付
     */
    UN_PAID,

    /**
     * 待收货
     */
    WAITING_FOR_RECEIVE,

    /**
     * 结束
     */
    DONE;
}
