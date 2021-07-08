package com.springboot.xmybatispagehelper.service;

import com.github.pagehelper.PageInfo;
import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import com.springboot.xmybatispagehelper.wrapper.PageHelperTreeRequest;

/**
 * @Author Lee
 * @Date 2021-7-8 09:00AM
 * @Location Wuxi
 * @Describe PageHelperTreeService
 * @Email 237594470@qq.com
 */
public interface PageHelperTreeService {

    PageInfo<PageHelperTree> queryAllTree(PageHelperTreeRequest pageHelperTreeRequest);

    void cleanTreeRedisCache(PageHelperTreeRequest pageHelperTreeRequest);

}
