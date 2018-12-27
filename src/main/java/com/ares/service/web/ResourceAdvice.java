package com.ares.service.web;

import com.ares.service.exception.BizException;
import com.ares.service.exception.ExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceAdvice {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ExceptionDetail> handleBizException(BizException e) {
        ExceptionDetail detail = new ExceptionDetail();
        detail.setCode(e.getCode());
        detail.setMessage(e.getMsg());
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}
