package com.springboot.xmybatispagehelper.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageHelperTree implements Serializable {

    private Integer id;

    private String name;

    private Integer pId;

    private Integer sort;

}
