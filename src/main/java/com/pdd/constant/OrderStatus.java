package com.pdd.constant;

/**
 * @Author: 安志辉
 * @Date: 2019/1/15 10:53
 * <p>
 * <p>
 * 订单状态常量
 */
public interface OrderStatus {

    public static final Integer ORDER_NOT_PAY = 1000;   //默认 未支付状态
    public static final Integer ORDER_FINSH_PAY = 1001;  //订单已发货
    public static final Integer ORDER_YI_FAHUO = 1002;  //订单已发货
    public static final Integer ORDER_YI_WANCHENG = 1003;  //订单已完成
}
