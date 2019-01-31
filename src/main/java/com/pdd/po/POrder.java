package com.pdd.po;

import java.io.Serializable;

public class POrder implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单创建时间
     */
    private String orderTime;

    /**
     * 0:未支付 1:已支付，未发货 2:已发货 3:已收货 4:失效订单
     */
    private Integer orderStatus = 0;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 地址ID
     */
    private Integer addressId;

    /**
     * 用户ID
     */
    private Integer uId;

    /**
     * 商品数量
     */
    private Integer productNumber;

    /**
     * 订单总金额
     */
    private Double totalSum;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 买家的支付宝唯一用户号（2088开头的16位纯数字）
     */
    private String buyerId;

    /**
     * 支付宝交易单号
     */
    private String alipayNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getAlipayNumber() {
        return alipayNumber;
    }

    public void setAlipayNumber(String alipayNumber) {
        this.alipayNumber = alipayNumber == null ? null : alipayNumber.trim();
    }
}