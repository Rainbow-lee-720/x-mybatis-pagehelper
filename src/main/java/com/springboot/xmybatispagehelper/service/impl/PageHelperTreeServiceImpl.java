package com.springboot.xmybatispagehelper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import com.springboot.xmybatispagehelper.mapper.PageHelperTreeMapper;
import com.springboot.xmybatispagehelper.service.PageHelperTreeService;
import com.springboot.xmybatispagehelper.wrapper.PageHelperTreeRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PageHelperTreeServiceImpl implements PageHelperTreeService {

    private static Logger logger = LoggerFactory.getLogger(PageHelperTreeServiceImpl.class);

    @Value(value = "${tree.prefix.name}")
    private String TREE_PREFIX_NAME;

    @Autowired
    private PageHelperTreeMapper pageHelperTreeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<PageHelperTree> queryAllTree(PageHelperTreeRequest pageHelperTreeRequest) {
        logger.info("###ClassName: {}, param: {}", this.getClass().getName(), pageHelperTreeRequest.toString());
        Integer pageNo = pageHelperTreeRequest.getPageNo();
        Integer pageSize = pageHelperTreeRequest.getPageSize();
        //分页
        PageHelper.startPage(pageNo, pageSize);
        //查询
        List<PageHelperTree> pageHelperTreeList = pageHelperTreeMapper.queryAllTreeCondition(pageHelperTreeRequest);
        if (ObjectUtils.isEmpty(pageHelperTreeList) || pageHelperTreeList.size() == 0) {
            logger.info("###ClassName: {}, data: {}", this.getClass().getName(), "data is empty");
            return new PageInfo<>(new ArrayList<PageHelperTree>());
        }
        //映射
//        List<PageHelperTree> treeList = pageHelperTreeList.stream()
//                .sorted(Comparator.comparing(PageHelperTree::getId)
//                .reversed())
//                .map(item -> {
//                    if (item.getName().contains("省")) {
//                        item.setName("[" + item.getName() + "]");
//                        logger.error(item.toString());
//                    }
//                    return item;
//                })
//                .collect(Collectors.toList());

        //封装
        PageInfo<PageHelperTree> pageInfo = new PageInfo<>(pageHelperTreeList);

        //过滤到Redis
        List<String> strList = pageHelperTreeList.stream().filter(_item1 -> _item1.getName().contains(TREE_PREFIX_NAME))
                .map(PageHelperTree::getName).collect(Collectors.toList());
        redisTemplate.opsForList().leftPushAll("TREE::REDIS::LIST", strList);

        logger.info("###ClassName: {}, data: {}", this.getClass().getName(), pageHelperTreeList.toString());
        return pageInfo;
    }
}
