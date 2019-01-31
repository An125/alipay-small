package com.pdd.service.impl;

import com.pdd.dto.ShopCarDto;
import com.pdd.enums.ParamEnum;
import com.pdd.mapper.ProductMapper;
import com.pdd.mapper.ShopCarMapper;
import com.pdd.po.Product;
import com.pdd.po.ShopCar;
import com.pdd.po.ShopCarExample;
import com.pdd.service.ShopCarService;
import com.pdd.utils.ReturnResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Author: 安志辉
 * @Date: 2019/1/22 9:42
 * 购物车服务
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Resource
    private ShopCarMapper shopCarMapper;

    @Resource
    private ProductMapper productMapper;

    /**
     * 添加购物车
     *
     * @param shopCarDto
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnResult addToCar(ShopCarDto shopCarDto) throws Exception {
        /**
         * 业务逻辑: 分3中情况
         *  first ：先根据商品ID到商品表中查询商品的库存，如果小于0，则返回
         *
         *  1.同一个支付宝ID，同一个商品ID，则更新购物车数量
         *  2.同一个支付宝ID，不同商品ID，则新加一个。
         *  3.不同支付宝ID，则新加一个
         */

        Product product = productMapper.selectByPrimaryKey(shopCarDto.getProductId());
        if (product.getStock() <= 0) {
            return ReturnResult.build(500, "fail", ParamEnum.LOW_STOCKS.getMsg());
        }
        //根据商品ID取出一条购物车信息
        ShopCarExample example = new ShopCarExample();
        example.createCriteria().andAlipayIdEqualTo(shopCarDto.getAlipayId());
        List<ShopCar> shopCars = shopCarMapper.selectByExample(example);
        ShopCar shopCar = null;
        if (shopCars.size() > 0 && shopCars != null) {
            for (int i = 0; i < shopCars.size(); i++) {
                ShopCar shopCar1 = shopCars.get(i);
                if (shopCar1.getProductId().equals(shopCarDto.getProductId()) && shopCar1.getAlipayId().equals(shopCarDto.getAlipayId())) {
                    shopCar = shopCar1;
                    break;
                }
            }
        }

//        System.out.println("数据库中商品id："+shopCar.getProductId());
//        System.out.println("客户端商品id："+shopCarDto.getProductId());


        //第一种情况：同一个支付宝ID，同一个商品ID，则更新购物车数量
        if (shopCar != null && shopCar.getProductId().equals(shopCarDto.getProductId()) && shopCar.getAlipayId().equals(shopCarDto.getAlipayId())) {
            //shopCar1.setProductPrice(shopCarDto.getProductPrice());
            shopCar.setProductNum(shopCarDto.getProductNum() + shopCar.getProductNum());
            // shopCar1.setAlipayId(shopCarDto.getAlipayId());
            //shopCar1.setProductId(shopCarDto.getProductId());
            shopCarMapper.updateByPrimaryKey(shopCar);
            return ReturnResult.build(200, "success", ParamEnum.SHOP_CAR_ADD_SUCCESS.getMsg());
        }//  第二种情况: 同一个支付宝ID，不同商品ID，则新加一个。
//        else if (shopCar != null && shopCarDto.getAlipayId().equals(shopCar.getAlipayId())) {
//            //ShopCar shopCar2 = new ShopCar();
//            shopCar.setProductPrice(shopCarDto.getProductPrice());
//            shopCar.setProductNum(shopCarDto.getProductNum());
//            shopCar.setAlipayId(shopCarDto.getAlipayId());
//            shopCar.setProductId(shopCarDto.getProductId());
//            shopCarMapper.insert(shopCar);
//            return ReturnResult.build(200, "success", ParamEnum.SHOP_CAR_ADD_SUCCESS.getMsg());
//        }
        else if (shopCars.size() <= 0 || shopCars == null || shopCar == null) {
            // 第三种情况: 不同支付宝ID，不同商品，则新加一个
            ShopCar shopCar3 = new ShopCar();
            shopCar3.setProductPrice(shopCarDto.getProductPrice());
            shopCar3.setProductNum(shopCarDto.getProductNum());
            shopCar3.setAlipayId(shopCarDto.getAlipayId());
            shopCar3.setProductId(shopCarDto.getProductId());
            shopCarMapper.insert(shopCar3);
            return ReturnResult.build(200, "success", ParamEnum.SHOP_CAR_ADD_SUCCESS.getMsg());
        }
        return ReturnResult.build(500, "fail", ParamEnum.SHOP_CAR_ADD_FAIL.getMsg());

    }

    /**
     * 删除购物车 《当前用户下》
     *
     * @param alipayId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnResult delToCar(String alipayId, Integer shopCarId) throws Exception {
        /**
         * 1. 根据支付宝用户ID 查询到当前用户下的购物车列表。
         * 2. 根据购物车ID删除对应的
         */

        //查询购物车列表
        List<ShopCar> shopCars = shopCarMapper.selectByAlipayId(alipayId);
        if (shopCars.size() <= 0 || shopCars == null) {
            return ReturnResult.build(200, "success", ParamEnum.NO_ORDER_WITH_USER.getMsg());
        }
        if (shopCars.size() > 0 && shopCars != null) {
            //根据购物车ID删除对应的购物项信息
            ShopCar shopCar = shopCarMapper.selectByPrimaryKey(shopCarId);
            if (shopCar != null) {
                shopCarMapper.deleteByPrimaryKey(shopCarId);
                return ReturnResult.build(200, "success", ParamEnum.DEL_SHOP_CAR_PRODUCT.getMsg());
            }
        }
        return null;
    }

    @Override
    public ReturnResult updateShopCar() throws Exception {
        return null;
    }

    /**
     * 清空购物车
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ReturnResult clearTocar(String alipayId) throws Exception {
        List<ShopCar> shopCars = shopCarMapper.selectByAlipayId(alipayId);
        if (shopCars.size() <= 0 || shopCars == null) {
            return ReturnResult.build(200, "success", "没有查询此人的购物车列表");
        } else {
            for (int i = 0; i <= shopCars.size(); i++) {
                ShopCarExample example = new ShopCarExample();
                example.createCriteria().andAlipayIdEqualTo(alipayId);
                shopCarMapper.deleteByExample(example);
            }
            return ReturnResult.build(200, "success", ParamEnum.SHOP_CAR_IS_NULL.getMsg());
        }
    }

    /**
     * 查询当前用户下的购物车列表
     * @param alipayId
     * @return
     * @throws Exception
     */
    @Override
    public ReturnResult getShopCars(String alipayId) throws Exception {
        List<ShopCar> shopCars = shopCarMapper.selectByAlipayId(alipayId);

        if (shopCars.size() > 0 && shopCars != null) {
            return ReturnResult.build(200, "success", shopCars);
        }
        return ReturnResult.build(404, "fail", ParamEnum.NO_GOODS.getMsg());
    }


}
