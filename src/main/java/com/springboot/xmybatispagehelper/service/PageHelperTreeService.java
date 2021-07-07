package com.springboot.xmybatispagehelper.service;

import com.github.pagehelper.PageInfo;
import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import com.springboot.xmybatispagehelper.wrapper.PageHelperTreeRequest;

public interface PageHelperTreeService {

    PageInfo<PageHelperTree> queryAllTree(PageHelperTreeRequest pageHelperTreeRequest);

}
