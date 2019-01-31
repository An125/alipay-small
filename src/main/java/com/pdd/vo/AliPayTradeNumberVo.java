package com.pdd.vo;

/**
 * @Author: 安志辉
 * @Date: 2019/1/17 15:45
 * <p>
 * 支付宝交易号和商户订单号视图对象
 */
public class AliPayTradeNumberVo {

    private String outTradeNo;  //商户订单号
    private String tradeNo;     //支付宝交易号

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public AliPayTradeNumberVo() {
    }
}
