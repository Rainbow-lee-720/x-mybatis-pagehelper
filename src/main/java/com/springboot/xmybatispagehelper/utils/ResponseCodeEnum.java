package com.springboot.xmybatispagehelper.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    /**
     * 请求响应码
     */
    OK(200, "success"),
    BAD_REQUEST(400, "bad request"),
    REQUEST_NOT_FOUND(404, "request not found"),
    SERVER_ERROR(500, "internal system error"),

    /**
     * 参数码
     */
    MISSING_TRACE_ID(888, "missing trace id"),
    REQUEST_PARAM_IS_NULL(1000, "request param is null"),
    DATA_IS_NULL_OR_EMPTY(2000, "data is null or empty");

    private Integer code;

    private String message;

}
