package com.springboot.xmybatispagehelper.wrapper;

import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import lombok.*;
import javax.validation.constraints.NotNull;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe PageHelperTreeRequest
 * @Email 237594470@qq.com
 */
@Getter
@Setter
@ToString
public class PageHelperTreeRequest extends PageHelperTree {

   @NotNull(message = "pageNo is not null")
   private Integer pageNo;

   @NotNull(message = "pageSize is not null")
   private Integer pageSize;

}
