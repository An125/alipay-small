package com.pdd.api;

import com.pdd.dto.OrderDto;
import com.pdd.enums.ParamEnum;
import com.pdd.service.OrderService;
import com.pdd.utils.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 安志辉
 * @Date: 2019/1/14 15:36
 */
@RestController
@RequestMapping("/order")
public class OrderApi {

    private Logger log = LoggerFactory.getLogger(OrderApi.class);

    @Autowired
    private OrderService orderService;

    /**
     * 订单生成API
     * @param orderDto
     * @return
     */
    @PostMapping("/create")
    public ReturnResult genreateOrder(@Valid OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }

        try {
            ReturnResult result = orderService.generateOrder(orderDto);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ReturnResult.build(500, "fail", "Server ERROR");
        }
    }

    /**
     * 删除订单API《根据支付宝交易流水号,实际是交易关闭》
     * @param trade_no
     * @return
     */
    @PostMapping("/del")
    public ReturnResult delOrder(@RequestParam("trade_no") String trade_no, @RequestParam("out_trade_no") String out_trade_no) {
        if ("".equals(trade_no) || trade_no.isEmpty() || ("").equals(out_trade_no) || out_trade_no.isEmpty()) {
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = orderService.delOrder(trade_no, out_trade_no);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 提交结算订单API
     * @param trade_no
     * @return
     */
    @PostMapping("/commit")
    public ReturnResult commitOrder(@RequestParam("trade_no") String trade_no) {
        if ("".equals(trade_no) || trade_no.isEmpty()) {
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = orderService.commitOrder(trade_no);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 订单列表API
     * 获取当前用户下的所有订单
     * @return
     */
    @PostMapping("/list")
    public ReturnResult getOrderList(@RequestParam("aliPayId") String aliPayId) {
        if ("".equals(aliPayId) || aliPayId == null) {
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = orderService.getOrdersList(aliPayId);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
