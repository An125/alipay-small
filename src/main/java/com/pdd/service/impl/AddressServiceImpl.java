package com.pdd.service.impl;

import com.pdd.enums.ParamEnum;
import com.pdd.mapper.AddressMapper;
import com.pdd.po.Address;
import com.pdd.po.AddressExample;
import com.pdd.service.AddressService;
import com.pdd.utils.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 安志辉
 * @Date: 2019/1/10 12:46
 */
@Service
public class AddressServiceImpl implements AddressService {

    private Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Resource
    private AddressMapper addressMapper;

    /**
     * 添加收货地址
     * @param address
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public ReturnResult addAddress(Address address) throws Exception {
        if (address == null || address.equals("")) {
            return ReturnResult.build(200, "success", "参数不能为空");
        }
        try {
            addressMapper.insert(address);
            return ReturnResult.build(200, "success");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * 删除收货地址  《根据支付宝唯一ID》
     * @param alipayId
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public ReturnResult delAddress(String alipayId, Integer addressId) throws Exception {


        try {
            // 1.先根据支付宝唯一ID去查询当前用户下的所有地址。
            AddressExample example = new AddressExample();
            example.createCriteria().andAlipayIdEqualTo(alipayId);
            List<Address> addressList = addressMapper.selectByExample(example);
            if (addressList.size() >0 && addressList != null) {
                // 2.然后再根据地址的ID去删除对应的地址
                addressMapper.deleteByPrimaryKey(addressId);
                return ReturnResult.build(200,"success",ParamEnum.DEL_ADDRESS_SUCCESS.getMsg());
            } else {
                return ReturnResult.build(200, "success", ParamEnum.NO_ADDRESS_LIST.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return ReturnResult.build(200, "success", ParamEnum.DEL_ADDRESS_FAIL.getMsg());
        }
    }

    /**
     * 修改收货地址
     * @param address
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ReturnResult updateAddress(Address address) throws Exception {



        // 1.根据支付宝唯一ID去查询当前用户下的所有地址
        String alipayId = address.getAlipayId();
        AddressExample example = new AddressExample();
        example.createCriteria().andAlipayIdEqualTo(alipayId);
        List<Address> addressList = addressMapper.selectByExample(example);

        if (addressList != null && addressList.size() > 0) {
            // 2.再根据地址ID去修改对应的地址
           // Address oldAddress = addressList.get(0);
            Address newAddress =  new Address();
            newAddress.setId(address.getId());
            newAddress.setUname(address.getUname());
            newAddress.setPhone(address.getPhone());
            newAddress.setAddress(address.getAddress());
            newAddress.setAlipayId(address.getAlipayId());
            addressMapper.updateByPrimaryKey(newAddress);
            return ReturnResult.build(200, "success", ParamEnum.UPDATE_ADDRESS_SUCCESS.getMsg());
        }

        return ReturnResult.build(200,"success",ParamEnum.PARAM_ERROR.getMsg());
    }


    /**
     * 地址列表 《根据支付宝唯一id》
     * 查询当前用户下的所有收货地址
     * @param alipayId
     * @return
     * @throws Exception
     */
    @Override
    public ReturnResult getUserAddressList(String alipayId) throws Exception {
        AddressExample example = new AddressExample();
        example.createCriteria().andAlipayIdEqualTo(alipayId);
        List<Address> addressList = addressMapper.selectByExample(example);
        if (addressList.size() <= 0 || addressList == null) {
            return ReturnResult.build(200, "success", ParamEnum.NO_ADDRESS_LIST.getMsg());
        } else {
            return ReturnResult.build(200, "success", addressList);
        }
    }
}