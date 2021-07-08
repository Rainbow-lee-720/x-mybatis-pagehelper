package com.springboot.xmybatispagehelper.aspect;

import com.alibaba.fastjson.JSON;
import com.springboot.xmybatispagehelper.exception.ParamNotExistException;
import com.springboot.xmybatispagehelper.exception.ParamValidErrorException;
import com.springboot.xmybatispagehelper.utils.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @Author Lee
 * @Date 2021-7-7 13:00PM
 * @Location Wuxi
 * @Describe LogAspect
 * @Email 237594470@qq.com
 */
@Aspect
@Slf4j
@Component
@Order(value = 2)
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<String> traceIdVar = new ThreadLocal<>();

    private ThreadLocal<String> requestUrlVar = new ThreadLocal<>();

    @Pointcut(value = "execution (* com.springboot.xmybatispagehelper.controller..*(..))")
    public void logPointcut() {

    }

    @Around(value = "logPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Long start = System.currentTimeMillis();
            Object[] args = pjp.getArgs();
            if (ObjectUtils.isEmpty(args) && args.length == 0) {
                logger.error("###LogAspect ===> error: {}", ResponseCodeEnum.REQUEST_PARAM_SIZE_0.getMessage());
                throw new ParamNotExistException(ResponseCodeEnum.REQUEST_PARAM_SIZE_0.getMessage());
            }
            String traceId = args[0].toString();
            traceIdVar.set(traceId);

            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            requestUrlVar.set(servletRequest.getRequestURI());

            Integer x = args.length - 2;
            Object o = args[x];

            logger.info("###begin ===> x-trace-id: {}, requestUrl: {}, param: {}", traceId,
                    servletRequest.getRequestURI(), o.toString());

            Object result = null;

            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                //执行出现异常会运行到此处
                logger.info("###System Exception::traceId: {}, requestUrl: {}, exception: {}, {}", traceId,
                        servletRequest.getRequestURI(), e.toString(), e.getStackTrace());
                throw e;
            }
            Long end = System.currentTimeMillis();

            logger.info("###end ===> x-trace-id: {}, requestUrl: {}, result: {}, spend: {}",
                            traceId, servletRequest.getRequestURI(), JSON.toJSONString(result), (end - start) + "ms");
            return result;
        } finally {
            traceIdVar.remove();
            requestUrlVar.remove();
        }
    }

    @AfterThrowing(throwing = "e", pointcut = "logPointcut()")
    public void doAfterThrowExp(JoinPoint joinPoint, Exception e) {
        logger.error("###LogAspect::Exception ===> x-trace-id: {}, requestUrl: {}, Signature: {}, {}",
                traceIdVar.get(), requestUrlVar.get(), joinPoint.getSignature().getName(), e);
    }

}
