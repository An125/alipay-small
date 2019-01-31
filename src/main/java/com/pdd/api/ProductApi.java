package com.pdd.api;

import com.pdd.enums.ParamEnum;
import com.pdd.service.ProductService;
import com.pdd.utils.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:41
 */
@RestController
@RequestMapping("/product")
public class ProductApi {

    private Logger log = LoggerFactory.getLogger(ProductApi.class);

    @Autowired
    private ProductService productService;

    /**
     * 商品列表 API
     * @return
     */
    @RequestMapping("/list")
    public ReturnResult getAll() {
        try {
            ReturnResult result = productService.getAll();
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商品详情 API
     * @param productId
     * @return
     */
    @RequestMapping("/detail")
    public ReturnResult productDeatil(@RequestParam("productId") Integer productId) {
        if (productId.equals("") || productId == null){
            return ReturnResult.build(200,"success", ParamEnum.PARAM_NOT_NULL.getMsg());
        }
        try {
            ReturnResult result = productService.productDetail(productId);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
