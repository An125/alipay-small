package com.pdd.vo;

/**
 * @Author: 安志辉
 * @Date: 2019/1/17 14:20
 * 视图对象
 */
public class UserVo {

    /**
     * 支付宝用户唯一 ID
     */
    private String alipayUserId;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 头像
     */
    private String imgUrl;

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
