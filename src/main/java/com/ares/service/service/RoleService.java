package com.ares.service.service;

import com.ares.service.domain.RoleListParam;
import com.ares.service.domain.TbRole;
import com.ares.service.exception.BizException;
import com.ares.service.persistence.RoleMapper;
import com.ares.service.util.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * services for role data.
 */
@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * query role .
     */
    public List<TbRole> listOfRole(Integer pageNumber, Integer pageSize, Integer roleId,
                                   String roleName)throws BizException {

        // param
        Integer realPageSize = Optional.ofNullable(pageSize).orElse(10);
        Integer start =
                Optional.ofNullable(pageNumber).map(page -> (page - 1) * realPageSize).orElse(0);
        List<Integer> roleIdListNotIn = new ArrayList<>();
        RoleListParam param =
                new RoleListParam(start, realPageSize, roleId, roleName, roleIdListNotIn);
        log.debug("start listOfRole. param:{} ", JsonTools.toJSONString(param));
        List<TbRole> list = roleMapper.listOfRole(param);
        log.debug("end listOfRole. list:{} ", JsonTools.toJSONString(list));
        return list;
    }

}
