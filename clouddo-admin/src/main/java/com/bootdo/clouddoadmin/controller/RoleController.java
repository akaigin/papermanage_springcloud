package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.RoleDO;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo
 * 角色
 */
@RequestMapping("/role")
@RestController
@Api("角色操作接口")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('admin:role:role')")
    @ApiOperation("获取角色列表并分页")
    @Log("获取角色列表")
    @GetMapping()
    PageUtils list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RoleDO> roleDOS = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roleDOS, total);
        return pageUtil;
    }

    @ApiOperation("根据用户id获取其角色列表")
    @Log("根据用户id获取其角色列表")
    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@ApiParam(name="userId", value = "所要搜索的用户id") @PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping
    ResultBean save(@RequestBody RoleDO roleDO){
        if(roleService.save(roleDO)>0){
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @PutMapping
    ResultBean update(@RequestBody RoleDO roleDO){
        if(roleService.update(roleDO)>0){
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

}
