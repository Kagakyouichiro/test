package com.example.usertestserver.nacosdiscovery.controller;

import cn.hutool.core.util.StrUtil;
import com.example.usertestserver.nacosdiscovery.common.CommonResult;
import com.example.usertestserver.nacosdiscovery.dto.User;
import com.example.usertestserver.nacosdiscovery.service.AccountService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    private final AccountService accountService;
    private final StringRedisTemplate stringRedisTemplate;

    public UserController(AccountService accountService, StringRedisTemplate stringRedisTemplate) {
        this.accountService = accountService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/find")
     CommonResult findBalance(@RequestParam(value = "userId",defaultValue = "1") Integer userId){
        //从缓存拿数据
        String jsonStr = stringRedisTemplate.opsForValue().get("balance");
        //如果从json取出的数据是空 则去数据库拿数据
        if (StrUtil.isBlank(jsonStr)){
            CommonResult result = accountService.findBalance(userId);
            stringRedisTemplate.opsForValue().set("balance", String.valueOf(result.getData()));
            return CommonResult.success(result);
        }else{
            return CommonResult.success(jsonStr);
        }


    }
    //用户消费接口
    @PutMapping("/comsumer")
    CommonResult comsumerGoods(@RequestParam(defaultValue = "1") Integer userId, @RequestParam Integer money) {
        int balance = (int) accountService.findBalance(userId).getData();
        if (balance - money < 0) {
            return CommonResult.fail("余额不足");
        } else {
            return CommonResult.success(accountService.comsumerGoods(userId, money));
        }
    }
    //用户退款接口
    @PutMapping("/reback")
    CommonResult rebackMoney(@RequestParam(defaultValue = "1") Integer userId, @RequestParam Integer money){
            return CommonResult.success(accountService.rebackMoney(userId,money));
    }
    //查询用户钱包金额变动明细的接口
    @GetMapping("/detail")
    CommonResult queryAccountDetails(@RequestParam(value = "userId",defaultValue = "1") Integer userId){
            return CommonResult.success(accountService.queryAccountDetails(userId));
    }

    @PostMapping("/invest")
    CommonResult investMoney(@RequestParam(defaultValue = "1") Integer userId, @RequestParam Integer money){
        return CommonResult.success(accountService.addTotal(userId, money));
    }

    @PostMapping("/add")
    CommonResult addUser(){
        User user = new User();
        //可用UUID 或雪花算法
        user.setUserId(2);
        user.setTotal(0);
        user.setBalance(0);
        user.setUsed(0);
        return CommonResult.success(accountService.addUser(user));
    }
}
