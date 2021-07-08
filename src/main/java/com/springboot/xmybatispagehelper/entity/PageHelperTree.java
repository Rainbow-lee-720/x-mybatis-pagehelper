package com.springboot.xmybatispagehelper.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe PageHelperTree
 * @Email 237594470@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageHelperTree implements Serializable {

    private static final long serialVersionUID = -577761045225670735L;

    private Integer id;

    private String name;

    private Integer pId;

    private Integer sort;

}
