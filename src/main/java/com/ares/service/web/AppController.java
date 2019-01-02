package com.ares.service.web;

import com.ares.service.domain.User;
import com.ares.service.domain.UserDto;
import com.ares.service.exception.ExceptionDetail;
import com.ares.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
public class AppController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/receiveUser", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<ExceptionDetail> userService(@RequestBody UserDto dto) throws UnsupportedEncodingException {
        userService.userService(dto);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setCode("0");
        detail.setMessage("成功");
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @RequestMapping("/api/getUser")
    @CrossOrigin
    public List<User> getUser(){
        return userService.getUser();
    }
}
