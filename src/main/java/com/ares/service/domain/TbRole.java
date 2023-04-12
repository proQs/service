package com.ares.service.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class of table tb_role.
 */
@Data
public class TbRole {

    private Integer roleId;
    private String roleName;
    private String roleNameZh;
    private Integer roleStatus;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
