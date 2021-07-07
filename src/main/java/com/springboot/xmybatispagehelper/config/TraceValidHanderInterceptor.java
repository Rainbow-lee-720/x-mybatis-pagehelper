package com.springboot.xmybatispagehelper.config;

import com.alibaba.druid.util.StringUtils;
import com.springboot.xmybatispagehelper.exception.TraceValidErrorException;
import com.springboot.xmybatispagehelper.exception.TraceValidNullException;
import com.springboot.xmybatispagehelper.utils.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Lee
 * @Date 2021-7-7 13:00PM
 * @Location Wuxi
 * @Describe TraceValidHanderInterceptor
 * @Email 237594470@qq.com
 */
@Slf4j
@Component
public class TraceValidHanderInterceptor implements HandlerInterceptor {

    public static Logger logger = LoggerFactory.getLogger(TraceValidNullException.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("====>TraceValidHanderInterceptor requestUrl: {}", request.getRequestURI());
        String traceId = request.getHeader("x-trace-id");
        if (StringUtils.isEmpty(traceId)) {
            response.setStatus(ResponseCodeEnum.MISSING_TRACE_ID.getCode());
            logger.error("missing request header -> 'x-trace-id'");
            throw new TraceValidNullException("missing request header -> 'x-trace-id'");
        }

        if (traceId.startsWith("x-") || traceId.contains("error")) {
            response.setStatus(ResponseCodeEnum.ERROR_TRACE_ID.getCode());
            logger.error("error request header -> 'x-trace-id'");
            throw new TraceValidErrorException("error request header -> 'x-trace-id'");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
