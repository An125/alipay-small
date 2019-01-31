package com.pdd.po;

import java.io.Serializable;

public class ShopCar implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品单价
     */
    private Double productPrice;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 支付宝用户唯一ID
     */
    private String alipayId;

    /**
     * 用户ID
     */
    private Integer uId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId == null ? null : alipayId.trim();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
}