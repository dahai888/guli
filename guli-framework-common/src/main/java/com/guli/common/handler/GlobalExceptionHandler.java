package com.guli.common.handler;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.util.ExceptionUtils;
import com.guli.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//定义一个切面
@Slf4j
public class GlobalExceptionHandler {
    /**
      异常通过切面和切点捕获到
     */
    //通用异常
    @ExceptionHandler(Exception.class)//具体处理异常对象
    @ResponseBody//以json的形式返回
    public R error(Exception e){
//        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return  R.error();
    }

    //特定异常
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
//        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(HttpMessageNotReadableException e){
//        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    //自定义异常

}
