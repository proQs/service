package com.ares.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleListParam extends BaseQueryParam {

    private Integer roleId;
    private String roleName;
    private String roleNameZh;
    private List<Integer> roleIdListNotIn;

    /**
     * init by start、pateSize、roleId、roleName.
     */
    public RoleListParam(Integer start, Integer pageSize, Integer roleId, String roleName, List<Integer> roleIdListNotIn) {
        super(start, pageSize, null);
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleIdListNotIn = roleIdListNotIn;
    }

}
