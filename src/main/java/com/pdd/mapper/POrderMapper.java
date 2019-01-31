package com.pdd.mapper;

import java.util.List;

import com.pdd.po.POrder;
import com.pdd.po.POrderExample;
import org.apache.ibatis.annotations.Param;

public interface POrderMapper {
    int countByExample(POrderExample example);

    int deleteByExample(POrderExample example);

    int deleteByPrimaryKey(Integer id);

    //根据支付宝交易号去删除
    int deleteByTradeNo(String trade_no);

    int insert(POrder record);

    int insertSelective(POrder record);

    List<POrder> selectByExample(POrderExample example);

    POrder selectByPrimaryKey(Integer id);

    //根据支付宝交易流水号查询订单
    POrder selectByTradeNo(String trade_no);

    int updateByExampleSelective(@Param("record") POrder record, @Param("example") POrderExample example);

    int updateByExample(@Param("record") POrder record, @Param("example") POrderExample example);

    int updateByPrimaryKeySelective(POrder record);

    int updateByPrimaryKey(POrder record);
}