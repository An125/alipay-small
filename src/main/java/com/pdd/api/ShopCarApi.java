package com.pdd.api;

import com.pdd.dto.ShopCarDto;
import com.pdd.enums.ParamEnum;
import com.pdd.service.ShopCarService;
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
 * @Date: 2019/1/22 11:12
 * 购物车API接口
 */
@RestController
@RequestMapping("/shopCar")
public class ShopCarApi {

    private Logger log = LoggerFactory.getLogger(ShopCarApi.class);

    @Autowired
    private ShopCarService shopCarService;

    @PostMapping("/add")
    public ReturnResult addToCar(@Valid ShopCarDto shopCarDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ReturnResult.build(200, "success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = shopCarService.addToCar(shopCarDto);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ReturnResult.build(500, "fail", "购物车添加失败");
        }

    }


    @PostMapping("/del")
    public ReturnResult delToCar(@RequestParam("alipayId") String alipayId, @RequestParam("shopCarId") Integer shopCarId) {
        if (!("").equals(alipayId) && alipayId != null && !("").equals(shopCarId) && shopCarId != null) {
            try {
                ReturnResult result = shopCarService.delToCar(alipayId, shopCarId);
                return result;
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return ReturnResult.build(500, "fail", ParamEnum.PARAM_NOT_NULL.getMsg());
    }

    @PostMapping("/clear")
    public ReturnResult clearToCar(@RequestParam("alipayId") String alipayId){
        if (!("").equals(alipayId) && alipayId != null){
            try {
                ReturnResult result = shopCarService.clearTocar(alipayId);
                return result;
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return ReturnResult.build(500,"fail",ParamEnum.PARAM_NOT_NULL.getMsg());
    }

    /**
     * 购物车列表《当前用户下》
     * @param alipayId
     * @return
     */
    @PostMapping("/list")
    public ReturnResult shopCarList(@RequestParam("alipayId") String alipayId) {
        if ("".equals(alipayId) || alipayId.isEmpty() || alipayId == null) {
            return ReturnResult.build(500, "fail", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult shopCars = shopCarService.getShopCars(alipayId);
            return shopCars;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
