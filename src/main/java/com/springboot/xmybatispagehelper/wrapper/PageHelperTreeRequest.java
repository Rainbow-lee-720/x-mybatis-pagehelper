package com.springboot.xmybatispagehelper.wrapper;

import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PageHelperTreeRequest extends PageHelperTree {

   @NotBlank(message = "pageNo is required")
   private Integer pageNo;

   @NotBlank(message = "pageSize is required")
   private Integer pageSize;

}
