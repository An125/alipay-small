package com.pdd.enums;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:06
 * 订单状态枚举类
 */
public enum OrderStatusEnum {

    ORDER_NOT_PAY(1000, "未支付"),
    ORDER_FINSH_PAY(1001, "已支付"),
    ORDER_YI_FAHUO(1002, "订单已发货"),
    ORDER_YI_WANCHENG(1003, "订单已完成");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
