package com.henu.papercommon.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    /*private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    ResultBean exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error(500, e.getMessage());
    }*/
}
