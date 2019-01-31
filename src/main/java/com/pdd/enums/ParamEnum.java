package com.pdd.enums;

/**
 * @Author: 安志辉
 * @Date: 2019/1/15 13:52
 */
public enum ParamEnum {

    lOGIN_SUCCES(01, "登陆成功"),
    lOGIN_FAIL(02, "登陆失败"),

    NO_GOODS(001,"没有商品，快去添加商品吧!!!"),
    NO_ACCORD_WITH_GOODS(002,"没有符合您的商品，去看看其他心仪的宝贝吧!!!"),
    COMMODITY_SHELVES(003,"用户购买的商品不存在或已下架，请检查商品ID"),
    LOW_STOCKS(004,"库存不足"),


    AUTO_NOT_CODE(800,"授权码不能为空"),
    SHOP_CAR_ADD_FAIL(801,"购物车添加失败"),
    SHOP_CAR_ADD_SUCCESS(802,"购物车添加成功"),
    DEL_SHOP_CAR_PRODUCT(803,"购物车中的商品已删除"),
    SHOP_CAR_IS_NULL(804,"购物车已清空，赶紧去添加商品吧"),

    PARAM_NOT_NULL(1002, "参数不能为空"),
    PARAM_ERROR(1003, "参数错误"),

    ADD_ADDRESS_SUCCESS(1004, "地址添加成功"),
    ADD_ADDRESS_FAIL(1005, "地址添加失败"),
    DEL_ADDRESS_SUCCESS(1006, "地址删除成功"),
    DEL_ADDRESS_FAIL(1007, "地址删除失败"),
    UPDATE_ADDRESS_SUCCESS(1008, "地址修改成功"),
    UPDATE_ADDRESS_FAIL(1009, "地址修改失败"),
    NO_ADDRESS_LIST(1010,"此用户还未添加收货地址"),

    NO_ORDER_LIST(600,"您还没有下单，赶紧将心仪的宝贝带回家吧！！！"),
    NO_ORDER_WITH_USER(601,"没有查询到相关订单"),
    ORDER_IS_DELETE(602,"订单已删除"),
    ORDER_FINISH_PAY_WAITING_FOR_SHIPMENT(603,"您的订单已付款，等待发货"),
    ;

    ParamEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

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
    }}
