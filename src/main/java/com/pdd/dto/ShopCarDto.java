package com.pdd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 安志辉
 * @Date: 2019/1/21 17:12
 * 商品信息Dto
 */
public class ShopCarDto {

    @NotNull(message = "商品ID不能为空")
    private Integer productId;

    @NotNull(message = "商品单价不能为空")
    private Double productPrice;   //商品单价

    @NotNull(message = "商品数量不能为空")
    private Integer productNum;   //商品数量

   @NotBlank(message = "支付宝用户ID不能为空")
    private String alipayId;

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
        this.alipayId = alipayId;
    }
}
