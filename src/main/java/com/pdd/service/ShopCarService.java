package com.pdd.service;

import com.pdd.dto.ShopCarDto;
import com.pdd.utils.ReturnResult;

/**
 * @Author: 安志辉
 * @Date: 2019/1/18 12:19
 */
public interface ShopCarService {

    //添加购物车
    ReturnResult addToCar(ShopCarDto productDto) throws Exception;

    //删除购物车《当前用户下》
    ReturnResult delToCar(String alipayId, Integer shopCarId) throws Exception;

    //修改购物车，主要修改商品的数量
    ReturnResult updateShopCar() throws Exception;

    //清空购物车
    ReturnResult clearTocar(String alipayId) throws Exception;

    //查看当前用户下购物车的所有信息
    ReturnResult getShopCars(String alipayId) throws Exception;


}
