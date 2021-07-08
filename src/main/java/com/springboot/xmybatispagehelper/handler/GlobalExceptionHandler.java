package com.springboot.xmybatispagehelper.handler;

import com.springboot.xmybatispagehelper.exception.CustomErrorExistException;
import com.springboot.xmybatispagehelper.utils.ResponseCodeEnum;
import com.springboot.xmybatispagehelper.utils.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Component
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = CustomErrorExistException.class)
    public ResponseEntity<Object> handleCustomErrorException(CustomErrorExistException customErrorExistException) {
        logger.error("###GlobalExceptionHandler: x-trace-id: {},exception: {}, {} ", customErrorExistException.getTraceId(),
                customErrorExistException.getMessage(), customErrorExistException.getStackTrace());
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(ResponseCodeEnum.CUSTOM_ERROR_EXCEPTION_CODE.getCode(), false,
                ResponseCodeEnum.CUSTOM_ERROR_EXCEPTION_CODE.getMessage(), null);
        return responseEntity;
    }


}
