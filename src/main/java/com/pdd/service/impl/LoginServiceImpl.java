package com.pdd.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.pdd.enums.ParamEnum;
import com.pdd.mapper.UserMapper;
import com.pdd.po.User;
import com.pdd.po.UserExample;
import com.pdd.service.LoginService;
import com.pdd.utils.ReturnResult;
import com.pdd.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: 安志辉
 * @Date: 2019/1/9 9:36
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;


    private static final String LoginUrl= "https://openapi.alipay.com/gateway.do";

    private static final String getUserMesUrl= "https://openapi.alipay.com/gateway.do";
    //AppID
    private static final String appId = "2018121462520809";
    //应用私钥
    private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHn" +
            "w1ho60swAvRTb9Ea/+bCfSuaaDHVEkIAYxs6e3acXfFq/RugloERkb/gF55LSfvKCyTH" +
            "+TuoRKy4wFjgZ6+ELUbvTcBrywJQXKtI1uj9kWGbDax4GMKT1kbHzmqRxADT4o7ogBHobFrWx" +
            "SeKFvNbvFyVSHX1znxTUnxXURPSYFHpnxuFGji+zNifre404G51XTzLhuGaS7K4wW0NfGGcwWT" +
            "+LoteJN2k+8vJv2ZSsePiAhHI+tHRqZsFyRmYS9ByzKw5NU5oABcXZibX/qmJvGwcRcob+CdbK4kb" +
            "XQQqXq5ZRugSztAj1vnO+jTjWAP5F7foBLoYed4EBhLzlyzAgMBAAECggEATINxWzqqguq+QBFq7UE" +
            "47dFKF/RD3vEwWrH09aVsqacGeaEvwAEOpAa6G3/izN6q/wRdUYvW7sI0+HOV3vvMAVMPulyf4fVgF" +
            "MC13/RoyTIxu0rl+Kitd62YifKt5+E7qYKAbHBTVBZnMDgsg6YlVNtYoLZi55DngbmnE+MYn" +
            "Nn5iA41ONUO7h5Y6i7h1NXl9IIGXEpYCFtN6mf8vKfdJeJTpr3Y7UY80xD+WVO0cmQqOaqk" +
            "ZZSgZJq0OfUEF+PVYElzyzVwoJCLbKvGZN+RmZ8ukTOanhIad7MKG+ZWrC4+wDdfkmwG1b" +
            "uK+uZY+PHu1c10jl0p5p/xgMY9za4yAQKBgQDTX4DeFUlOMnkleF4m4pGLesZ9RnSaZku" +
            "XwtQMawzW99P+cE4arW53tdSYTNlf6aIWna3Bf5mxwQ+sqUO1GhBQwLxipd1rNxY" +
            "ItarSX4fTA1+7R5GL2fzSKvPaqR4EoxkKjOqD2qHm3IJL4Snkou582X/uEJMtcQ2" +
            "9AgBiqVoqMwKBgQCkQT53UTP1rj31baqq31DbPaHezWLboNjgXsXLmSPURMMMcwtSqoQJGuV" +
            "3Plj8g36sY9qKBY5dhPmHOSUQ2faTAaB1+VK++oNZK9IP/spqLdR9tUhtSYPER+v72szOo+jZ" +
            "NFDUfsA2AswEAD7FyKki4qr91WwVLCbCQNVEzh2DgQKBgCpkhRwFeOZOCS4Srg9ucXJf9V86ujg" +
            "dPBv73zt+XNzgZ4woSgHftsAbn7/L6ezqlq+igC7GwLR92/7tE79Gsu7/OMkwKvBN5AVrKg93S" +
            "W5wxspw2CgMWEBujOnV8UdMzsDm8kHvn3kF0LwXNKe+ZcZ0eMvuVxZODktSE3G2NXcxAoGAeG9SU" +
            "wBaXdidm3gGvHUzfx2Is6naw3kDrDgxRWCLEdg9P8KW2c0nNNngyHcRnkz4M4Sft7XtNgsLPygaUmnOzu" +
            "lRc08VaeybJGXqB0pltkbWWDn02hUeJWwqzqcWpqkuURx36+boHnIcjw5y+GhyA585ixbSwg92d9Y7yd" +
            "BreYECgYEAj2JJVh9mawBVK0QOwfTcj74HiOGFlF9bS5FQn8nDDM7gpS64z/irodooC1H5CKLc/0Uc9YCg" +
            "bYGWgA3d3R3cfSdPvdrYSZ+J7nkavW5ct82TTWY8XxhDTfh9JkCm+483l5MvWE+vlyixDTnbjNAWzLVsPAD" +
            "R+IPlE0Fx5MTR6ek=";
    //支付宝公钥
    private static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CFLeOP3" +
            "RG6x+SWaulirEuuBnY9K3l6e2jT28bWyLJQexDvm02xDsCFxxli583t+NQ5DkCsfQDp9wo" +
            "QnhRXACTw48ktjCXXkXQKfIOp1zkRwjDKTnFphYzT0i7uSECs2rSe0uH1X8YM7AvtFYRG" +
            "iny8XybntH4IgsLqdeBhhrZFk+47b6Ef3o7ppXJTmZkSQJpnDRo8eJpydwjj4MfbGhzkDlTDWe" +
            "mMPJQSbL+Bxbj83iwpq6vwzLPYWm/NXF7lA+3+ZszmbxAMLmsB5a0oYeTd+UfHQjT+B0Q1RpSR9r" +
            "3gpPRdGLWarnVULnQjpB9mNiPfZb7KTm09j6t3lXShuJQIDAQAB";

    /**
     * 用户登陆服务
     * @param authCode
     * @return
     * @throws Exception
     */
    public ReturnResult userLogin(String authCode) throws Exception {
        //TODO
        // 1.客户端传入授权码
        // 2.根据授权码调用蚂蚁金服接口，获取token和买家支付宝唯一ID
        // 3.根据token取用户信息，然后查看是否存在支付宝唯一ID，如果有登陆成功，并更新用户信息。若没有唯一ID直接插入
        // 4.返回客户端信息，使用UserVo对象进行封装

        AlipayClient alipayClient = new DefaultAlipayClient(LoginUrl, appId, privateKey, "json", "GBK", alipayPublicKey, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        // request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");

        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
        String accessToken  = response.getAccessToken();
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println("支付宝用户唯一id：" + response.getUserId());
            System.out.println("token令牌:" + response.getAccessToken());   //访问令牌。通过该令牌调用需要授权类接口


            //根据token调用支付宝API取出用户列表信息
            AlipayClient alipayClient2 = new DefaultAlipayClient(getUserMesUrl,appId,privateKey,"json","GBK",alipayPublicKey,"RSA2");
            AlipayUserInfoShareRequest request2 = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse response2 = alipayClient2.execute(request2,accessToken);
            if(response.isSuccess()){
                System.out.println("调用成功");
                System.out.println("头像地址:"+response2.getAvatar());
                System.out.println("用户昵称:"+response2.getNickName());
                System.out.println("用户ID:"+response2.getUserId());

                //支付宝唯一用户ID
                String userId = response2.getUserId();
                UserExample example = new UserExample();
                example.createCriteria().andAlipayUserIdEqualTo(userId);
                List<User> userList = userMapper.selectByExample(example);

                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                if (userList.size()>0 && userList != null){
                    //如果存在用户信息，则更新用户信息，如果用户在支付宝中更改了信息，可以达到同步。
                    User user = userList.get(0);
                    user.setImgUrl(response2.getAvatar());
                    user.setUname(response2.getNickName());
                    user.setLastLoginTime(format.format(new Date()));
                    userMapper.updateByPrimaryKey(user);
                }else{
                    //如果没有用户信息，则新插入
                    User user = new User();
                    user.setUname(response2.getNickName());
                    user.setImgUrl(response2.getAvatar());
                    user.setAlipayUserId(response2.getUserId());
                    user.setLastLoginTime(format.format(new Date()));
//                    user.setPhone(response2.getPhone());
                    user.setToken(accessToken);
                    userMapper.insert(user);
                }

                //返回信息
                UserVo userVo = new UserVo();
                userVo.setImgUrl(response2.getAvatar());
                userVo.setUname(response2.getNickName());
                userVo.setAlipayUserId(response2.getUserId());
                return ReturnResult.build(200,"success",userVo);

            } else {
                System.out.println("调用失败,没有用户信息");
            }
            return ReturnResult.build(200, "success", ParamEnum.lOGIN_SUCCES.getMsg());
        } else {
            System.out.println("调用失败");
            return ReturnResult.build(200, "fail", ParamEnum.lOGIN_FAIL.getMsg());
        }
    }
}
