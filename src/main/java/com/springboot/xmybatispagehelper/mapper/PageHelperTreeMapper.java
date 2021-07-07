package com.springboot.xmybatispagehelper.mapper;

import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageHelperTreeMapper {

    List<PageHelperTree> queryAllTreeCondition(PageHelperTree pageHelperTree);

}
