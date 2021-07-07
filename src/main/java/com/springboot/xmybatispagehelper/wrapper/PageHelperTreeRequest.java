package com.springboot.xmybatispagehelper.wrapper;

import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PageHelperTreeRequest extends PageHelperTree {

   @NotNull(message = "pageNo is not null")
   private Integer pageNo;

   @NotNull(message = "pageSize is not null")
   private Integer pageSize;

}
