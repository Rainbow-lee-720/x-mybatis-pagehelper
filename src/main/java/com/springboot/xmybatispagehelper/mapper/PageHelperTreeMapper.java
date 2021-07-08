package com.springboot.xmybatispagehelper.mapper;

import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe PageHelperTreeMapper
 * @Email 237594470@qq.com
 */
@Mapper
public interface PageHelperTreeMapper {

    List<PageHelperTree> queryAllTreeCondition(PageHelperTree pageHelperTree);

}
