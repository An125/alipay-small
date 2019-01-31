package com.pdd.service;

import com.pdd.utils.ReturnResult;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:38
 * 商品列表服务接口
 */
public interface ProductService {

    //商品列表
    ReturnResult getAll() throws Exception;

    //商品详情
    ReturnResult productDetail(Integer productId) throws Exception;
}
