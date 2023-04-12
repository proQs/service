package com.ares.service.persistence;

import com.ares.service.domain.RoleListParam;
import com.ares.service.domain.TbRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper about tb_role.
 */
public interface RoleMapper {

    TbRole queryRoleById(@Param("roleId") Integer roleId);

    Integer countOfRole(@Param("param") RoleListParam param);

    List<TbRole> listOfRole(@Param("param") RoleListParam param);
}
