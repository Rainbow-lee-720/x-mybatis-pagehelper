package com.springboot.xmybatispagehelper.aspect;

import com.springboot.xmybatispagehelper.exception.ParamValidErrorException;
import com.springboot.xmybatispagehelper.utils.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

/**
 * @Author Lee
 * @Date 2021-7-7 13:00PM
 * @Location Wuxi
 * @Describe ValidParamBindingAspect
 * @Email 237594470@qq.com
 */
@Aspect
@Slf4j
@Component
@Order(value = 1)
public class ValidParamBindingAspect {

    private static Logger logger = LoggerFactory.getLogger(ValidParamBindingAspect.class);

    @Pointcut(value = "execution (* com.springboot.xmybatispagehelper.controller..*(..))")
    public void validParamPointcut() {

    }

    @Before(value = "validParamPointcut()")
    public void doBefore(JoinPoint jp) throws ParamValidErrorException {
        Object[] args = jp.getArgs();
        if (!ObjectUtils.isEmpty(args) && args.length != 0) {
            Object param = jp.getArgs()[args.length - 1];
            String traceId = (String) args[0];
            if (param instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) param;
                if (bindingResult.hasErrors()) {
                    String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
                    logger.error("###ValidParamBindingAspect ===> x-trace-id: {}, requestParamValidError: {}",traceId, errorMessage);
                    throw new ParamValidErrorException(ResponseCodeEnum.REQUEST_PARAM_VALID_ERROR.getMessage());
                }
            }
        }
    }

}
