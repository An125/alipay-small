package com.pdd.service;

import com.alipay.api.domain.OpenApiRoyaltyDetailInfoPojo;
import com.pdd.dto.OrderDto;
import com.pdd.utils.ReturnResult;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @Author: 安志辉
 * @Date: 2019/1/14 14:49
 */
public interface OrderService {

    /**
     * 订单生成
     * @return
     * @throws Exception
     */
    ReturnResult generateOrder(OrderDto orderDto) throws Exception;

    /**
     * 删除订单
     * @param trade_no
     * trade_no  支付宝交易流水号
     * out_trade_no  订单号
     * @return
     * @throws Exception
     */
    ReturnResult delOrder(String trade_no,String out_trade_no) throws Exception;

    /**
     * 提交订单
     * @return
     * @throws Exception
     */
    ReturnResult commitOrder(String trade_no) throws Exception;


    /**
     * 获取当前用户下的所有订单
     * @return
     * @throws Exception
     */
    ReturnResult getOrdersList(String aliPayId) throws Exception;
}
