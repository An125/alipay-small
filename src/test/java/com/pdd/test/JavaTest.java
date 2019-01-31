package com.pdd.test;

import com.pdd.mapper.AddressMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 安志辉
 * @Date: 2019/1/16 8:51
 */
public class JavaTest {

    @Autowired
    private AddressMapper addressMapper;

//    @Test
//    public void alipay() {
//
//
//        String url = "https://openapi.alipay.com/gateway.do";
//
//        //APPID
//        String appId = "2018121462520809";
//        //应用私钥
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHnw1ho60swAvRTb9Ea/+bCfSuaaDHVEkIAYxs6e3acXfFq/RugloERkb/gF55LSfvKCyTH+TuoRKy4wFjgZ6+ELUbvTcBrywJQXKtI1uj9kWGbDax4GMKT1kbHzmqRxADT4o7ogBHobFrWxSeKFvNbvFyVSHX1znxTUnxXURPSYFHpnxuFGji+zNifre404G51XTzLhuGaS7K4wW0NfGGcwWT+LoteJN2k+8vJv2ZSsePiAhHI+tHRqZsFyRmYS9ByzKw5NU5oABcXZibX/qmJvGwcRcob+CdbK4kbXQQqXq5ZRugSztAj1vnO+jTjWAP5F7foBLoYed4EBhLzlyzAgMBAAECggEATINxWzqqguq+QBFq7UE47dFKF/RD3vEwWrH09aVsqacGeaEvwAEOpAa6G3/izN6q/wRdUYvW7sI0+HOV3vvMAVMPulyf4fVgFMC13/RoyTIxu0rl+Kitd62YifKt5+E7qYKAbHBTVBZnMDgsg6YlVNtYoLZi55DngbmnE+MYnNn5iA41ONUO7h5Y6i7h1NXl9IIGXEpYCFtN6mf8vKfdJeJTpr3Y7UY80xD+WVO0cmQqOaqkZZSgZJq0OfUEF+PVYElzyzVwoJCLbKvGZN+RmZ8ukTOanhIad7MKG+ZWrC4+wDdfkmwG1buK+uZY+PHu1c10jl0p5p/xgMY9za4yAQKBgQDTX4DeFUlOMnkleF4m4pGLesZ9RnSaZkuXwtQMawzW99P+cE4arW53tdSYTNlf6aIWna3Bf5mxwQ+sqUO1GhBQwLxipd1rNxYItarSX4fTA1+7R5GL2fzSKvPaqR4EoxkKjOqD2qHm3IJL4Snkou582X/uEJMtcQ29AgBiqVoqMwKBgQCkQT53UTP1rj31baqq31DbPaHezWLboNjgXsXLmSPURMMMcwtSqoQJGuV3Plj8g36sY9qKBY5dhPmHOSUQ2faTAaB1+VK++oNZK9IP/spqLdR9tUhtSYPER+v72szOo+jZNFDUfsA2AswEAD7FyKki4qr91WwVLCbCQNVEzh2DgQKBgCpkhRwFeOZOCS4Srg9ucXJf9V86ujgdPBv73zt+XNzgZ4woSgHftsAbn7/L6ezqlq+igC7GwLR92/7tE79Gsu7/OMkwKvBN5AVrKg93SW5wxspw2CgMWEBujOnV8UdMzsDm8kHvn3kF0LwXNKe+ZcZ0eMvuVxZODktSE3G2NXcxAoGAeG9SUwBaXdidm3gGvHUzfx2Is6naw3kDrDgxRWCLEdg9P8KW2c0nNNngyHcRnkz4M4Sft7XtNgsLPygaUmnOzulRc08VaeybJGXqB0pltkbWWDn02hUeJWwqzqcWpqkuURx36+boHnIcjw5y+GhyA585ixbSwg92d9Y7ydBreYECgYEAj2JJVh9mawBVK0QOwfTcj74HiOGFlF9bS5FQn8nDDM7gpS64z/irodooC1H5CKLc/0Uc9YCgbYGWgA3d3R3cfSdPvdrYSZ+J7nkavW5ct82TTWY8XxhDTfh9JkCm+483l5MvWE+vlyixDTnbjNAWzLVsPADR+IPlE0Fx5MTR6ek=";
//        //支付宝公钥
//        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CFLeOP3RG6x+SWaulirEuuBnY9K3l6e2jT28bWyLJQexDvm02xDsCFxxli583t+NQ5DkCsfQDp9woQnhRXACTw48ktjCXXkXQKfIOp1zkRwjDKTnFphYzT0i7uSECs2rSe0uH1X8YM7AvtFYRGiny8XybntH4IgsLqdeBhhrZFk+47b6Ef3o7ppXJTmZkSQJpnDRo8eJpydwjj4MfbGhzkDlTDWemMPJQSbL+Bxbj83iwpq6vwzLPYWm/NXF7lA+3+ZszmbxAMLmsB5a0oYeTd+UfHQjT+B0Q1RpSR9r3gpPRdGLWarnVULnQjpB9mNiPfZb7KTm09j6t3lXShuJQIDAQAB";
//
//
//        //以下是参数
//        String out_trade_no = "PDDZN201901151547";  //商户订单号
//        String total_amount = "";  //订单总金额
//        String subject = "口罩，白色口罩";  //订单标题
//
//        AlipayClient alipayClient = new DefaultAlipayClient(url, appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
//
//        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
//
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"PDDZN201901151547\"," +
//                "\"total_amount\":100," +
//                "\"buyer_id\":\"2088002009318210\"," +
//                "\"subject\":\"口罩 白色口罩\"}");
//
////        request.setBizContent("{" +
////                "out_trade_no:" + "PDDZN201901151547" +
////                "total_amount:" + "88.88" +
////                "buyer_id:" + "2088002009318210" +
////                "subject:" + "口罩，白色口罩"
////                + "}");
//
//
//        AlipayTradeCreateResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//            System.out.println("商户订单号是:" + response.getOutTradeNo());
//            System.out.println("支付宝交易号:" + response.getTradeNo());
//        } else {
//            System.out.println("调用失败");
//        }
//
//    }


//        AddressExample example = new AddressExample();
//        List<Address> addressList = addressMapper.selectByExample(example);
//        for (int i = 0; i<addressList.size(); i++){
//            System.out.println(addressList.get(i).getId());
//        }
//    @Test
//    public void addresList(){
//    }


//    @Test
//    public void testThread() {
//
//        class Threadtest implements Runnable {
//            private int number = 200;
//
//            @Override
//            public void run() {
//
//                while (true) {
//                    synchronized (this) {
//                        if (number > 0) {
//                            System.out.println(Thread.currentThread().getName() + "卖了第" + number + "张票");
//                            number--;
//                        } else {
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//
////        for(int i = 0; i<4; i++){
////            Thread thread = new Thread(new Threadtest());
////            thread.setName(i+"号售票员");
////            thread.start();
////        }
//
//
//        Thread thread1 = new Thread(new Threadtest());
//        thread1.setName("1号售票员");
//        Thread thread2 = new Thread(new Threadtest());
//        thread2.setName("2号售票员");
//        Thread thread3 = new Thread(new Threadtest());
//        thread3.setName("3号售票员");
//        Thread thread4 = new Thread(new Threadtest());
//        thread4.setName("4号售票员");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//    }


}
