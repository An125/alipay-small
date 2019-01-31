package com.pdd.po;

import java.io.Serializable;

public class Product implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 零售价
     */
    private Double retailPrice;

    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 商品详细描述
     */
    private String productDetailDesc;

    /**
     * 商品图片地址
     */
    private String productImgAddress;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public String getProductDetailDesc() {
        return productDetailDesc;
    }

    public void setProductDetailDesc(String productDetailDesc) {
        this.productDetailDesc = productDetailDesc == null ? null : productDetailDesc.trim();
    }

    public String getProductImgAddress() {
        return productImgAddress;
    }

    public void setProductImgAddress(String productImgAddress) {
        this.productImgAddress = productImgAddress == null ? null : productImgAddress.trim();
    }
}