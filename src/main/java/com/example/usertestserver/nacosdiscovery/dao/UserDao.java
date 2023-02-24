package com.example.usertestserver.nacosdiscovery.dao;


import com.example.usertestserver.nacosdiscovery.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {


    //查询用户钱包余额
    int queryBalance(@Param("userId") Integer userId);
    //用户消费的接口
    int comsumer(@Param("userId") Integer userId,Integer money);
    //用户退款元接口
    int returnMoney(@Param("userId") Integer userId, Integer money);
    //充值余额
    int addMoney(@Param("userId") Integer userId,Integer money);
    //查询用户钱包金额变动明细的接口
    User selectAccountDetail(@Param("userId") Integer userId);
    //添加用户
    int addUser(User user);

}
