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

    private static final long serialVersionUID = -577761045225670735L;

    private Integer id;

    private String name;

    private Integer pId;

    private Integer sort;

}
