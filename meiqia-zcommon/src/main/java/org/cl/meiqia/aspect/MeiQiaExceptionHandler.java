package org.cl.meiqia.aspect;

import org.cl.meiqia.exception.MeiQiaException;
import org.cl.meiqia.struct.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class MeiQiaExceptionHandler {

    @ExceptionHandler(value = MeiQiaException.class)
    public Result<String> handlerException(MeiQiaException exception){
        exception.printStackTrace();
        return new Result<>(false,500,exception.getMsg());
    }
}
