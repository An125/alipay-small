package com.pdd.dto;

import com.pdd.constant.OrderStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 安志辉
 * @Date: 2019/1/14 15:48
 * 订单传输对象
 */
public class OrderDto implements Serializable {


    /**
     * 0:未支付 1:已支付，未发货 2:已发货 3:已收货 4:失效订单
     */
    private Integer orderStatus = 0;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Integer productId;

    /**
     * 地址ID
     */
    @NotNull(message = "地址ID不能为空")
    private Integer addressId;

    /**
     * 用户ID
     */
    //@NotNull(message = "用户ID不能为空")
    private Integer uId;



    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    private Integer productNumber;

    /**
     * 订单总金额   =《商品单价*商品数量》
     */
    private Double totalSum;

    /**
     * 订单标题
     */
    @NotBlank(message = "订单标题不能为空")
    private String subject;


    /**
     * 买家的支付宝唯一用户号（2088开头的16位纯数字）
     */
    @NotBlank(message = "支付宝唯一用户ID不能为空")
    private String buyerId;


    public OrderDto() {
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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
