package com.example.usertestserver.nacosdiscovery.service.Impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.C;
import com.example.usertestserver.nacosdiscovery.common.CommonResult;
import com.example.usertestserver.nacosdiscovery.dao.UserDao;
import com.example.usertestserver.nacosdiscovery.dto.User;
import com.example.usertestserver.nacosdiscovery.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserDao userDao;

    public AccountServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //查询用户钱包余额
    @Override
    public CommonResult findBalance(Integer userId) {

        return CommonResult.success( userDao.queryBalance(userId));
    }
    //用户消费100元的接口
    @Override
    @Transactional
    public CommonResult comsumerGoods(Integer userId, Integer money) {

        return CommonResult.success(userDao.comsumer(userId,money));
    }
    //用户退款20元接口
    @Override
    @Transactional
    public CommonResult rebackMoney(Integer userId, Integer money) {
        return CommonResult.success(userDao.returnMoney(userId, money));
    }
    //查询用户钱包金额变动明细的接口
    @Override
    public CommonResult queryAccountDetails(Integer userId) {
        return CommonResult.success(userDao.selectAccountDetail(userId));
    }

    @Override
    public CommonResult addTotal(Integer userId, Integer money) {
        return CommonResult.success(userDao.addMoney(userId, money));
    }

    @Override
    public CommonResult addUser(User user) {

        return CommonResult.success(userDao.addUser(user));
    }
}
