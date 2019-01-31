package com.pdd.api;

import com.pdd.enums.ParamEnum;
import com.pdd.service.LoginService;
import com.pdd.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 15:05
 *
 * 用户登录服务接口
 */
@RestController
@RequestMapping("/user")
public class LoginApi {

    @Autowired
    private LoginService loginService;


    /**
     * 登陆接口
     * @param autoCode
     * @return
     */
    @PostMapping("/login")
    public ReturnResult login(@RequestParam("autoCode") String autoCode) {
        if ("".equals(autoCode) || autoCode ==null){
            return ReturnResult.build(200,"success", ParamEnum.AUTO_NOT_CODE.getMsg());
        }
        try {
            ReturnResult result = loginService.userLogin(autoCode);
            System.out.println("登陆成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
