package com.pdd.service;

import com.pdd.utils.ReturnResult;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:35
 */
public interface LoginService {

    ReturnResult userLogin(String authCode) throws Exception;
}
