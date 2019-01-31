package com.pdd.mapper;

import com.pdd.po.ShopCar;
import com.pdd.po.ShopCarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper {
    int countByExample(ShopCarExample example);

    int deleteByExample(ShopCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    List<ShopCar> selectByExample(ShopCarExample example);

    //根据商品id查询
    ShopCar selectByProductId(Integer productId);

    //根据支付宝用户ID查询购物车列表
    List<ShopCar> selectByAlipayId(String alipayId);

    ShopCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByExample(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
}