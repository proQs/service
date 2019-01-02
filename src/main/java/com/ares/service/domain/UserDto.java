/*
 * Copyright (c) 2017. Shanghai Zhenhui Information Technology Co,. ltd.
 * All rights are reserved.
 */
package com.ares.service.domain;

import lombok.Data;

@Data
public class UserDto {

    private String timestamp;

    private String realname;

    private String sign;

    private String mobile;

    private String cardno;

    private String email;
}
