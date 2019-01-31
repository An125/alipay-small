package com.pdd.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.OpenApiRoyaltyDetailInfoPojo;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.pdd.dto.OrderDto;
import com.pdd.enums.ParamEnum;
import com.pdd.mapper.POrderMapper;
import com.pdd.mapper.ProductMapper;
import com.pdd.po.POrder;
import com.pdd.po.POrderExample;
import com.pdd.po.Product;
import com.pdd.po.ProductExample;
import com.pdd.service.OrderService;
import com.pdd.utils.GenerateOrderNoUtil;
import com.pdd.utils.ReturnResult;
import com.pdd.vo.AliPayTradeNumberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: 安志辉
 * @Date: 2019/1/14 14:50
 * 订单服务类
 */
@Service
public class OrderServiceImpl implements OrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private POrderMapper orderMapper;

    @Resource
    private ProductMapper productMapper;


    //正式环境URL，创建 ，删除 和提交订单都是此Url
    private static final String url = "https://openapi.alipay.com/gateway.do";
    //APPID
    private static final String appId = "2018121462520809";
    //应用私钥
    private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHnw1ho60swAvRTb9Ea/+bCfSuaaDHVEkIAYxs6e3acXfFq/RugloERkb/gF55LSfvKCyTH+TuoRKy4wFjgZ6+ELUbvTcBrywJQXKtI1uj9kWGbDax4GMKT1kbHzmqRxADT4o7ogBHobFrWxSeKFvNbvFyVSHX1znxTUnxXURPSYFHpnxuFGji+zNifre404G51XTzLhuGaS7K4wW0NfGGcwWT+LoteJN2k+8vJv2ZSsePiAhHI+tHRqZsFyRmYS9ByzKw5NU5oABcXZibX/qmJvGwcRcob+CdbK4kbXQQqXq5ZRugSztAj1vnO+jTjWAP5F7foBLoYed4EBhLzlyzAgMBAAECggEATINxWzqqguq+QBFq7UE47dFKF/RD3vEwWrH09a" +
            "VsqacGeaEvwAEOpAa6G3/izN6q/wRdUYvW7sI0+HOV3vvMAVMPulyf4fVgFMC13/RoyTIxu0rl+Kitd62YifKt5+E7qYKAbHBTVBZnMDgsg6YlVNtYoLZi55DngbmnE+MYnNn5iA41ONUO7h5Y6i7h1NXl9IIGXEpYCFtN6mf8vKfdJeJTpr3Y7UY80xD+WVO0cmQqOaqkZZSgZJq0OfUEF+PVYElzyzVwoJCLbKvGZN+RmZ8ukTOanhIad7MKG+ZWrC4+wDdfkmwG1buK+uZY+PHu1c10jl0p5p/xgMY9za4yAQKBgQDTX4DeFUlOMnkleF4m4pGLesZ9RnSaZkuXwtQMawzW99P+cE4arW53tdSYTNlf6aIWna3Bf5mxwQ+sqUO1GhBQwLxipd1rNxYItarSX4fTA1+7R5GL2fzSKvPaqR4EoxkKjOqD2qHm3IJL4Snkou582X/uEJMtcQ29AgBiq" +
            "VoqMwKBgQCkQT53UTP1rj31baqq31DbPaHezWLboNjgXsXLmSPURMMMcwtSqoQJGuV3Plj8g36sY9qKBY5dhPmHOSUQ2faTAaB1+VK++oNZK9IP/spqLdR9tUhtSYPER+v72szOo+jZNFDUfsA2AswEAD7FyKki4qr91WwVLCbCQNVEzh2DgQKBgCpkhRwFeOZOCS4Srg9ucXJf9V86ujgdPBv73zt+XNzgZ4woSgHftsAbn7/L6ezqlq+igC7GwLR92/7tE79Gsu7/OMkwKvBN5AVrKg93SW5wxspw2CgMWEBujOnV8UdMzsDm8kHvn3kF0LwXNKe+ZcZ0eMvuVxZODktSE3G2NXcxAoGAeG9SUwBaXdidm3gGvHUzfx2Is6naw3kDrDgxRWCLEdg9P8KW2c0nNNngyHcRnkz4M4Sft7XtNgsLPygaUmnOzulRc08VaeybJGXqB0pltkb" +
            "WWDn02hUeJWwqzqcWpqkuURx36+boHnIcjw5y+GhyA585ixbSwg92d9Y7ydBreYECgYEAj2JJVh9mawBVK0QOwfTcj74HiOGFlF9bS5FQn8nDDM7gpS64z/irodooC1H5CKLc/0Uc9YCgbYGWgA3d3R3cfSdPvdrYSZ+J7nkavW5ct82TTWY8XxhDTfh9JkCm+483l5MvWE+vlyixDTnbjNAWzLVsPADR+IPlE0Fx5MTR6ek=";

    //支付宝公钥
    private static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CFLeOP3RG6x+SWaulirEuuBnY9K3l6e2jT28bWyLJQexDvm02xDs" +
            "CFxxli583t+NQ5DkCsfQDp9woQnhRXACTw48ktjCXXkXQKfIOp1zkRwjDKTnFphYzT0i7uSECs2rSe0uH1X8YM7AvtFYRGiny8XybntH4I" +
            "gsLqdeBhhrZFk+47b6Ef3o7ppXJTmZkSQJpnDRo8eJpydwjj4MfbGhzkDlTDWemMPJQSbL+Bxbj83iwpq6vwzLPYWm" +
            "/NXF7lA+3+ZszmbxAMLmsB5a0oYeTd+UfHQjT+B0Q1RpSR9r3gpPRdGLWarnVULnQjpB9mNiPfZb7KTm09j6t3lXShuJQIDAQAB";

    /**
     * 创建订单
     * @return
     * @throws Exception
     */
    @Override
    @Transactional  //事务管理
    public ReturnResult generateOrder(OrderDto orderDto) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 1.补全实例对象
        // 1.1 生成订单号  订单号生成规则:《PPDZN+系统当前时间+两位随机数》
        String orderNo = GenerateOrderNoUtil.generateOrderNo();
        POrder pOrder = new POrder();
        pOrder.setOrderId(orderNo);
        pOrder.setOrderTime(format.format(new Date()));

        //获取商品ID，根据商品ID去查询商品的单价，然后乘以数量，计算订单总金额
        Integer productId = orderDto.getProductId();
        ProductExample example = new ProductExample();
        example.createCriteria().andIdEqualTo(productId);
        List<Product> products = productMapper.selectByExample(example);
        if (products.size() <= 0 || products == null) {
            //如果没有查询到相关商品则直接返回
            return ReturnResult.build(200, "success", ParamEnum.COMMODITY_SHELVES.getMsg());
        }
        //查询到对应商品并取出单价
        Product product = products.get(0);
        //使用BeanUtils，将dto中的属性拷贝到po中，序列化到DB
        //BeanUtils.copyProperties(pOrder,orderDto);
        //取出零售价
        Double retailPrice = product.getRetailPrice();
        //计算总金额 = 商品单价 * 商品数量
        Double totalSum = retailPrice * orderDto.getProductNumber();
        pOrder.setTotalSum(totalSum);
        pOrder.setSubject(orderDto.getSubject());
        pOrder.setProductId(orderDto.getProductId());
        pOrder.setAddressId(orderDto.getAddressId());
        pOrder.setuId(orderDto.getuId());
        pOrder.setProductNumber(orderDto.getProductNumber());
        pOrder.setBuyerId(orderDto.getBuyerId());


        // 2.根据订单号调用支付宝创建订单接口
        // 2.2 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(url, appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
        // 2.3 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.create.
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();

        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNo + "\"," +
                "\"total_amount\":" + totalSum + "," +
                "\"buyer_id\":\"" + orderDto.getBuyerId() + "\"," +
                "\"subject\":\"" + orderDto.getSubject() + "\"}");


        AlipayTradeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error(e.getErrMsg());
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println("商户订单号是:" + response.getOutTradeNo());
            System.out.println("支付宝交易号:" + response.getTradeNo());

            //设置支付宝交易号
            pOrder.setAlipayNumber(response.getTradeNo());
            try {
                // 3.将订单插入数据库
                orderMapper.insert(pOrder);

                Product product1 = new Product();
                product1.setStock(-1);
            } catch (Exception e) {
                log.error(e.getMessage());
                e.getStackTrace();
                return null;
            }
            // 4.返回订单号和支付宝交易单号
            AliPayTradeNumberVo tradeNumberVo = new AliPayTradeNumberVo();
            tradeNumberVo.setOutTradeNo(response.getOutTradeNo());
            tradeNumberVo.setTradeNo(response.getTradeNo());
            return ReturnResult.build(200, "success", tradeNumberVo);
        } else {
            System.out.println("调用失败");
            return null;
        }
    }


    /**
     * 删除订单《支付宝交易流水号》
     * @param trade_no:支付宝交易流水号 out_trade_no:商户订单号
     * @return
     * @throws Exception dssa
     */
    @Override
    @Transactional
    public ReturnResult delOrder(String trade_no, String out_trade_no) throws Exception {

        // 1.根据交付宝交易流水号，和商户订单号调用支付宝交易关闭接口
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
                "\"trade_no\":\"" + trade_no + "\"," +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"operator_id\":\"YX01\"" +
                "  }");

        AlipayTradeCloseResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
            // 2.如果调用成功，则根据支付宝交易流水号或者订单号去数据库中查询，
            //   建议使用支付宝交易流水号 官方说明>>>《订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no》

            POrder pOrder = orderMapper.selectByTradeNo(trade_no);
            if (pOrder != null) {
                orderMapper.deleteByTradeNo(trade_no);

                AliPayTradeNumberVo vo = new AliPayTradeNumberVo();
                vo.setTradeNo(trade_no);
                vo.setOutTradeNo(out_trade_no);
                return ReturnResult.build(200, "交易已关闭", vo);
            }
            // 3.返回信息
            // return ReturnResult.build(200,"success",ParamEnum.ORDER_IS_DELETE.getMsg());
        } else {
            System.out.println("调用失败,删除订单失败");
        }
        return null;
    }

    /**
     * 提交订单
     *
     * @param
     * @param trade_no
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ReturnResult commitOrder(String trade_no) throws Exception {
        // 1.请求支付宝接口

//        AlipayClient alipayClient = new DefaultAlipayClient(url, appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
//        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
//        request.setBizContent("{" +
//                "\"out_request_no\":\"" + out_request_no + "\"," +  //订单号
//                "\"trade_no\":\"" + trade_no + "\"," +     //支付宝交易流水号
//                "      \"royalty_parameters\":[{" +    //分账明细信息
//                //"        \"trans_out\":\"" + royalty_parameters.getTransOut() + "\"," +   //分账支出方账号，即分出账号的支付宝用户ID
//               // "\"trans_in\":\"" + royalty_parameters.getTransIn() + "\"," +   //分账收入方账号，即分出账号的支付宝用户ID
//                //"\"amount\":" + royalty_parameters.getAmount() + "," +    //分账的金额，单位为元
//                //"\"amount_percentage\":100," +    //分账信息中分账百分比。取值范围为大于0，少于或等于100的整数。
//                //"\"desc\":\"" + royalty_parameters.getDesc() + "\"" +   //分账的描述信息
//                //"        }]," +
//                "\"operator_id\":\"A0001\"" +           //操作员ID
//                "  }");
//        AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//            // 2.修改订单状态  可根据支付宝交易流水号查询订单，再修改订单状态。
//            POrder pOrder = orderMapper.selectByTradeNo(trade_no);
//            if (pOrder != null) {
//                pOrder.setOrderStatus(1);
//                orderMapper.updateByPrimaryKey(pOrder);
//                return ReturnResult.build(200, "success", ParamEnum.ORDER_FINISH_PAY_WAITING_FOR_SHIPMENT.getMsg());
//            }
//
//        } else {
//            System.out.println("调用失败，提交支付订单失败");
//        }


        // 1.根据支付宝交易流水号，查询数据库，找到对应的订单，修改状态为，已支付，代发货,修改库存。
        POrder pOrder = orderMapper.selectByTradeNo(trade_no);
        if (pOrder != null) {
            pOrder.setOrderStatus(1);
            orderMapper.updateByPrimaryKey(pOrder);
            Integer productId = pOrder.getProductId();
            Product product = productMapper.selectByPrimaryKey(productId);
            Integer oldStock = product.getStock();
            Integer newStock = oldStock - 1;
            product.setStock(newStock);
            productMapper.updateByPrimaryKey(product);

            return ReturnResult.build(200, "success", ParamEnum.ORDER_FINISH_PAY_WAITING_FOR_SHIPMENT.getMsg());
        }
        return ReturnResult.build(200, "success", ParamEnum.NO_ORDER_WITH_USER.getMsg());
    }

    /**
     * 获取当前用户下的所有订单
     * @param aliPayId
     * @return
     * @throws Exception
     */
    @Override
    public ReturnResult getOrdersList(String aliPayId) throws Exception {
        POrderExample example = new POrderExample();
        example.createCriteria().andBuyerIdEqualTo(aliPayId);
        List<POrder> orderList = orderMapper.selectByExample(example);
        if (orderList.size() > 0 && orderList != null) {
            return ReturnResult.build(200, "success", orderList);
        }
        return ReturnResult.build(200, "success", ParamEnum.NO_ORDER_LIST.getMsg());

    }

}
