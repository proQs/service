package com.ares.service.web;

import com.ares.service.domain.UserDto;
import com.ares.service.exception.ExceptionDetail;
import com.ares.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
public class AppController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/receiveUser")
    public ResponseEntity<ExceptionDetail> userService(@RequestBody UserDto dto) throws UnsupportedEncodingException {
        userService.userService(dto);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setCode("0");
        detail.setMessage("成功");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}
