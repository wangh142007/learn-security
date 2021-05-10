package com.hao.learnsecurity.common.exceptions;

import com.baomidou.mybatisplus.extension.api.R;
import com.hao.learnsecurity.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Wang Hao
 * @date ：Created in 2020/12/4 16:33
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常类
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    public Result<String> globalExceptionHandler(HttpServletRequest request, GlobalException e) {
        log.error(e.getCodeMsg().getMsg());
        return Result.failed(e.getCodeMsg().getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> bindExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error(e.getClass().toString());
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        //此处仅取第一个
        for (ObjectError error : errors) {
            errorMsg.append(error.getDefaultMessage()).append(" ");
        }
        return Result.failed(errorMsg.toString());
    }

    /**
     * 其他异常类
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getClass().toString());
        log.error(e.toString());
        e.printStackTrace();
        return Result.failed(e.toString());
    }

}
