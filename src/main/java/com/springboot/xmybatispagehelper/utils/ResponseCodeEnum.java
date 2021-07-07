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
    REQUEST_PARAM_VALID_ERROR(600, "request param valid error"),

    /**
     * 参数码
     */
    MISSING_TRACE_ID(888, "missing trace id"),
    ERROR_TRACE_ID(889, "error trace id"),
    REQUEST_PARAM_IS_NULL(1000, "request param is null"),
    REQUEST_PARAM_SIZE_0(1500, "request params is 0"),
    DATA_IS_NULL_OR_EMPTY(2000, "data is null or empty");

    private Integer code;

    private String message;

}
