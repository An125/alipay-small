package com.pdd.po;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Address implements Serializable {
    /**
     * 
     */

    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名不能为空")
    private String uname;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "收货人手机号不能为空")
    private String phone;

    /**
     * 收货地址
     */
    @NotBlank(message = "收货人地址不能为空")
    private String address;

    /**
     * 支付宝用户唯一ID
     */
    @NotBlank(message = "支付宝唯一ID不能为空")
    private String alipayId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId == null ? null : alipayId.trim();
    }
}