package com.springboot.xmybatispagehelper.utils;

import lombok.*;

import java.io.Serializable;

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
