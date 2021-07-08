package com.springboot.xmybatispagehelper.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.xmybatispagehelper.entity.PageHelperTree;
import com.springboot.xmybatispagehelper.exception.CustomErrorExistException;
import com.springboot.xmybatispagehelper.service.PageHelperTreeService;
import com.springboot.xmybatispagehelper.utils.ResponseCodeEnum;
import com.springboot.xmybatispagehelper.utils.ResponseEntity;
import com.springboot.xmybatispagehelper.wrapper.PageHelperTreeRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lee
 * @Date 2021-7-7 13:00PM
 * @Location Wuxi
 * @Describe Redis Cache And PageHelper Pract
 * @Email 237594470@qq.com
 */
@RestController
@RequestMapping(value = "/page/tree")
@Slf4j
public class PageHelperTreeController {

    private static Logger logger = LoggerFactory.getLogger(PageHelperTreeController.class);

    @Autowired
    private PageHelperTreeService pageHelperTreeService;

    @GetMapping(value = "/queryAll")
    public ResponseEntity<Map<String, Object>> pageHelperTreeQuery(@RequestHeader(value = "x-trace-id") String traceId,
                                                                   @Valid PageHelperTreeRequest pageHelperTreeRequest,
                                                                   BindingResult bindingResult) {
        //校验
//        if (bindingResult.hasErrors()) {
//            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
//            logger.error("###x-trace-id: {}, requestParamValidError: {}",traceId, errorMessage);
//            return new ResponseEntity(ResponseCodeEnum.REQUEST_PARAM_VALID_ERROR.getCode(), false,
//                    ResponseCodeEnum.REQUEST_PARAM_VALID_ERROR.getMessage(), errorMessage);
//        }

//                long start = System.currentTimeMillis();
//        logger.error("###Request ====> x-trace-id: {}, ClassName: {}, requestUrl: {}, param: {}", traceId, this.getClass().getName(),
//                "/page/tree/queryAll", pageHelperTreeRequest.toString());

        //校验
//        if (ObjectUtils.isEmpty(pageHelperTreeRequest.getPageNo())
//                || ObjectUtils.isEmpty(pageHelperTreeRequest.getPageSize())) {
//            return new ResponseEntity(ResponseCodeEnum.REQUEST_PARAM_IS_NULL.getCode(), false,
//                    ResponseCodeEnum.REQUEST_PARAM_IS_NULL.getMessage(), null);
//        }

        try {
            //查询
            PageInfo<PageHelperTree> pageInfo = pageHelperTreeService.queryAllTree(pageHelperTreeRequest);
            if (ObjectUtils.isEmpty(pageInfo.getList())) {
                logger.error("###x-trace-id: {}, error: {}",traceId, ResponseCodeEnum.DATA_IS_NULL_OR_EMPTY.getMessage());
                return new ResponseEntity(ResponseCodeEnum.DATA_IS_NULL_OR_EMPTY.getCode(), false,
                        ResponseCodeEnum.DATA_IS_NULL_OR_EMPTY.getMessage(), null);
            }

            //封装
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", pageInfo.getList());
            responseMap.put("total", pageInfo.getTotal());
            responseMap.put("pages", pageInfo.getPages());
            responseMap.put("pageNum", pageInfo.getPageNum());
            responseMap.put("pageSize", pageInfo.getPageSize());

//            long end = System.currentTimeMillis();
//            logger.error("###Response ====> x-trace-id: {}, ClassName: {}, requestUrl: {}, response: {}, spend: {}", traceId, this.getClass().getName(),
//                    "/page/tree/queryAll", responseMap.toString(), (end - start) + "ms");

            return new ResponseEntity<Map<String, Object>>(ResponseCodeEnum.OK.getCode(),
                    true, ResponseCodeEnum.OK.getMessage(), responseMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<Map<String, Object>>(ResponseCodeEnum.SERVER_ERROR.getCode(),
                    false, ResponseCodeEnum.SERVER_ERROR.getMessage(), null);
        }
    }

    @RequestMapping(value = "/exception")
    public ResponseEntity<Object> customException(@RequestHeader(value = "x-trace-id") String traceId,
                                                  @Valid PageHelperTreeRequest pageHelperTreeRequest,
                                                  BindingResult bindingResult) {
        throw new CustomErrorExistException(traceId, 3333, "this is customException!");
    }

}
