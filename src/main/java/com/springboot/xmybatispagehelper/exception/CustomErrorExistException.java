package com.springboot.xmybatispagehelper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe CustomErrorExistException 自定义异常[用于Controller全局异常捕捉测试]
 * @Email 237594470@qq.com
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomErrorExistException extends RuntimeException{

    private String traceId;

    private Integer code;

    private String message;

}
