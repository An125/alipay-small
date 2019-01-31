package com.pdd.service;

import com.pdd.po.Address;
import com.pdd.utils.ReturnResult;

/**
 * @Author: 安志辉
 * @Date: 2019/1/10 12:34
 * 地址管理服务接口
 */
public interface AddressService {
    /**
     * 添加收货地址
     * @return
     */
    ReturnResult addAddress(Address address) throws Exception;

    /**
     * 删除收货地址
     *
     * @return
     */
    ReturnResult delAddress(String alipayId,Integer addressId) throws Exception;

    /**
     * 修改收获地址
     * @return
     */
    ReturnResult updateAddress(Address address) throws Exception;

    /**
     * 地址列表《支付宝唯一ID》
     * 查询当前用户下的所有收货地址
     * @return
     * @throws Exception
     */
    ReturnResult getUserAddressList(String alipayId) throws  Exception;
}
