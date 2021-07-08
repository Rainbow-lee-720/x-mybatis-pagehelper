package com.springboot.xmybatispagehelper.utils;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe ResponseEntity
 * @Email 237594470@qq.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ResponseEntity <T> implements Serializable {

    private Integer code;

    private Boolean flag;

    private String message;

    private T data;

}
