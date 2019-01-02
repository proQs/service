/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */

package com.ares.service.service;

import com.ares.service.domain.User;
import com.ares.service.domain.UserDto;
import com.ares.service.exception.BizException;
import com.ares.service.persistence.UserMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void userService(UserDto dto) throws UnsupportedEncodingException {
        DateTime time = null;
        try {
            time = new DateTime(new Date(Long.valueOf(dto.getTimestamp()) * 1000));
        } catch (Exception e) {
            throw new BizException("10001", "签名不合法");
        }
        if (Seconds.secondsBetween(time, new DateTime()).getSeconds() > 10 * 60) {
            throw new BizException("10001", "签名不合法");
        }
        String encodeStr = DigestUtils.md5DigestAsHex(("023f612e-6597-4f4e-bd31-1cb658ca6b64" + dto.getTimestamp()).getBytes("UTF-8"));
        if (!encodeStr.substring(0, 8).equalsIgnoreCase(dto.getSign())) {
            throw new BizException("10001", "签名不合法");
        }
        if (StringUtils.isEmpty(dto.getRealname())) {
            throw new BizException("10002", "姓名为空");
        }
        if (StringUtils.isEmpty(dto.getMobile())) {
            throw new BizException("10003", "手机为空");
        }
        if (StringUtils.isEmpty(dto.getCardno())) {
            throw new BizException("10004", "身份证为空");
        }
        if (StringUtils.isEmpty(dto.getEmail())) {
            throw new BizException("10006", "邮箱为空");
        }
        int count = userMapper.selectCount(new EntityWrapper<User>().eq("mobile", dto.getMobile()));
        if (count > 0) {
            throw new BizException("10005", "手机号码已注册");
        }
        User u = new User();
        BeanUtils.copyProperties(dto, u);
        userMapper.insert(u);
    }

    public List<User> getUser() {
        return userMapper.selectList(null);
    }
}
