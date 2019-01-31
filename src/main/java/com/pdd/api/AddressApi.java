package com.pdd.api;

import com.pdd.enums.ParamEnum;
import com.pdd.po.Address;
import com.pdd.service.AddressService;
import com.pdd.utils.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * @Author: 安志辉
 * @Date: 2019/1/10 13:19
 */
@RestController
@RequestMapping("/address")
public class AddressApi {

    private Logger log = LoggerFactory.getLogger(AddressApi.class);

    @Autowired
    private AddressService addressService;

    /**
     * 添加收货地址API
     * @param address
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    public ReturnResult addAddress(@Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }

        try {
            addressService.addAddress(address);
            log.info("添加收货地址成功");
            return ReturnResult.build(200, "success", ParamEnum.ADD_ADDRESS_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return ReturnResult.build(200, "fail", ParamEnum.ADD_ADDRESS_FAIL.getMsg());
    }

    /**
     * 删除地址API
     * @param alipayId
     * @return
     */
    @PostMapping("/del")
    public ReturnResult delAddress(@RequestParam("alipayId") String alipayId,@RequestParam("addressId") Integer addressId) {
        if ("".equals(alipayId) || alipayId == null || ("").equals(addressId) || addressId == null) {
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = addressService.delAddress(alipayId, addressId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return ReturnResult.build(200, "fail", ParamEnum.DEL_ADDRESS_FAIL.getMsg());
    }

    /**
     * 修改地址API
     * @param address
     * @return 只允许修改收货地址，收货人姓名，收货人手机号
     */
    @PostMapping("/update")
    public ReturnResult updatelAddress(@Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ReturnResult.build(200, "success", ParamEnum.PARAM_ERROR.getMsg());
        }
        try {
            ReturnResult result = addressService.updateAddress(address);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * 地址列表API
     * @return
     */
    @RequestMapping("/list")
    public ReturnResult getUserAddressList(@RequestParam("alipayId") String alipayId) {
        if ("".equals(alipayId) || alipayId == null) {
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        } else {
            try {
                ReturnResult userAddressList = addressService.getUserAddressList(alipayId);
                return userAddressList;
            } catch (Exception e) {
                log.info(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }

}
