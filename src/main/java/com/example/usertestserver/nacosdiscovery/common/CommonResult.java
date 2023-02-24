package com.example.usertestserver.nacosdiscovery.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by macro on 2019/8/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    public static CommonResult success(){
        return new CommonResult(Constants.CODE_200,"",null);
    }

    public static CommonResult success(Object data){
        return new CommonResult(Constants.CODE_200, "", data);
    }

    public static CommonResult fail(String code, String message){
        return new CommonResult(code, message, null);
    }

    public static CommonResult fail(Object data){
        return new CommonResult(Constants.CODE_500, "系统错误", data);
    }

}