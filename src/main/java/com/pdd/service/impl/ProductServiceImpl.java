package com.pdd.service.impl;

import com.pdd.enums.ParamEnum;
import com.pdd.mapper.ProductMapper;
import com.pdd.po.Product;
import com.pdd.po.ProductExample;
import com.pdd.service.ProductService;
import com.pdd.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:40
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Resource
    private ProductMapper productMapper;

    /**
     * 商品列表
     *
     * @return
     * @throws Exception
     */
    public ReturnResult getAll() throws Exception {
        ProductExample example = new ProductExample();
        List<Product> list = productMapper.selectByExample(example);
        if (list.size() > 0 && !list.isEmpty()) {
            return ReturnResult.build(200, "success", list);
        } else {
            return ReturnResult.build(404, "success", ParamEnum.NO_GOODS.getMsg());
        }
    }

    /**
     * 商品详情 《根据商品id》
     * @param productId
     * @return
     */
    public ReturnResult productDetail(Integer productId) throws Exception {
        ProductExample example = new ProductExample();
        example.createCriteria().andIdEqualTo(productId);
        List<Product> list = productMapper.selectByExample(example);
        if (list.size() > 0 && !list.isEmpty()) {
            Product product = list.get(0);
            return ReturnResult.build(200, "success", product);
        } else {
            return ReturnResult.build(200, "success", ParamEnum.NO_ACCORD_WITH_GOODS.getMsg());
        }
    }


}
