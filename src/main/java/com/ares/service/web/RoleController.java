/**
 * description: TChainBase
 * <p>
 * author: saberqu
 * date: 2022/3/31 下午4:02
 * copyright Copyright@2022
 **/

package com.ares.service.web;

import com.ares.service.domain.TbRole;
import com.ares.service.exception.BizException;
import com.ares.service.service.RoleService;
import com.ares.service.util.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * query role list.
     */
    @GetMapping(value = "/roleList")
    public List<TbRole> queryRoleList(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "roleId", required = false) Integer roleId,
            @RequestParam(value = "roleName", required = false) String roleName)
            throws BizException {
        Instant startTime = Instant.now();
        log.info(
                "start queryRoleList.  startTime:{} pageNumber:{} pageSize:{} roleId:{} roleName:{}",
                startTime.toEpochMilli(),
                pageNumber, pageSize, roleId, roleName);

        // query
        List<TbRole> pageResponse = roleService.listOfRole(pageNumber, pageSize, roleId, roleName);

        log.info("end queryRoleList useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(),
                JsonTools.toJSONString(pageResponse));
        return pageResponse;
    }
}
