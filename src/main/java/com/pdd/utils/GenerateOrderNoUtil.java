package com.pdd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: 安志辉
 * @Date: 2019/1/14 14:42
 * 订单号生成工具类
 */
public class GenerateOrderNoUtil {

    private static final String title = "PDDZN"; //公司开头

    public static String generateOrderNo() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");  //系统当前时间
        String date = sdf.format(new Date());

        Random random = new Random();
        int i = random.nextInt(10);//随机数

        StringBuffer buffer = new StringBuffer();
        StringBuffer result = buffer.append(title + date + i);

        return result.toString();
    }
}
