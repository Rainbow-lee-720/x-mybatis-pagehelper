package com.springboot.xmybatispagehelper;

import com.github.pagehelper.PageInfo;
import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import com.springboot.xmybatispagehelper.service.PageHelperTreeService;
import com.springboot.xmybatispagehelper.service.impl.PageHelperTreeServiceImpl;
import com.springboot.xmybatispagehelper.wrapper.PageHelperTreeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class RedisTestApplicationTest {

    @Autowired
    private PageHelperTreeService pageHelperTreeService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        PageHelperTreeRequest pageHelperTreeRequest = new PageHelperTreeRequest();
        pageHelperTreeRequest.setPageNo(1);
        pageHelperTreeRequest.setPageSize(5);
        PageInfo<PageHelperTree> pageInfo = pageHelperTreeService.queryAllTree(pageHelperTreeRequest);
        //封装
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data", pageInfo.getList());
        responseMap.put("total", pageInfo.getTotal());
        responseMap.put("pages", pageInfo.getPages());
        responseMap.put("pageNum", pageInfo.getPageNum());
        responseMap.put("pageSize", pageInfo.getPageSize());
        long end = System.currentTimeMillis();
        log.error("=====>data: {}, spend: {}", responseMap.toString(), (end - start) + "ms");
    }

    @Test
    public void test2() {
        Object o = redisTemplate.opsForList().range(PageHelperTreeServiceImpl.REDIS_TREE_LIST_KEY, 0, -1);
        log.error("==========redis data: {}", o.toString());
    }

    @Test
    public void test3() {
        PageHelperTreeRequest pageHelperTreeRequest = new PageHelperTreeRequest();
        pageHelperTreeRequest.setPageNo(2);
        pageHelperTreeService.cleanTreeRedisCache(pageHelperTreeRequest);
    }
}
