package com.ares.service.web;

import com.ares.service.exception.BizException;
import com.ares.service.exception.ExceptionDetail;
import com.ares.service.util.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ResourceAdvice {

    @ResponseBody
    @ExceptionHandler(BizException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDetail> handleBizException(BizException e) {
        ExceptionDetail detail = new ExceptionDetail();
        detail.setCode(e.getCode());
        detail.setMessage(e.getMsg());
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    /**
     * parameter exception:TypeMismatchException
     */
    @ResponseBody
    @ExceptionHandler(value = TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDetail> typeMismatchExceptionHandler(TypeMismatchException ex) {
        log.error("catch typeMismatchException:[]", ex);

        ExceptionDetail detail = new ExceptionDetail();
        detail.setMessage(ex.getMessage());
        log.warn("typeMismatchException return:{}", JsonTools.toJSONString(detail));
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }


    /**
     * catch：BadSqlGrammarException.
     */
    @ResponseBody
    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDetail> BadSqlGrammarExceptionHandler(RuntimeException exc, HttpServletRequest request) {
        log.error("catch BadSqlGrammarException:[]", exc);

        ExceptionDetail detail = new ExceptionDetail();
        detail.setMessage(exc.getMessage());
        log.warn("BadSqlGrammarException return:{}", JsonTools.toJSONString(detail));
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    /**
     * catch：RuntimeException.
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDetail> exceptionHandler(RuntimeException exc, HttpServletRequest request) {
        log.error("catch RuntimeException:[]", exc);

        ExceptionDetail detail = new ExceptionDetail();
        detail.setMessage(exc.getMessage());
        log.warn("system RuntimeException return:{}", JsonTools.toJSONString(detail));
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}
