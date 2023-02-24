package com.example.usertestserver.nacosdiscovery.service;

import com.example.usertestserver.nacosdiscovery.common.CommonResult;
import com.example.usertestserver.nacosdiscovery.dto.User;

public interface AccountService {
    //查询用户钱包余额
    CommonResult findBalance(Integer userId);
    //用户消费的接口
    CommonResult comsumerGoods(Integer userId,Integer money);
    //用户退款的接口
    CommonResult rebackMoney(Integer userId,Integer money);
    //查询用户钱包金额变动明细的接口
    CommonResult queryAccountDetails(Integer userId);
    //充值余额
    CommonResult addTotal(Integer userId, Integer money);
    //添加用户
    CommonResult addUser(User user);
}
