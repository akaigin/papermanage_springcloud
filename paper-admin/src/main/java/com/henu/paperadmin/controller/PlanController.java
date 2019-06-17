package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.RoleDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;
import com.henu.paperadmin.service.PlanService;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/plan")
@RestController
@Api("计划安排操作接口")
public class PlanController {
    @Autowired
    PlanService planService;


    //@PreAuthorize("hasAuthority('admin:role:role')")
    @ApiOperation("获取计划安排列表并分页")
    @Log("获取计划安排列表")
    @GetMapping("/list")
    PageUtils list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        String createUser= SecuityUtils.getCurrentUser().getName();
        UserDO planDO=new UserDO();
        planDO.setName(createUser);
        List<PlanDO> planDOS = planService.list(query,planDO);
        Map<String,Object> map=new HashMap<>();
        map.put("createUser",createUser);
        int total = planService.count(map);
        PageUtils pageUtil = new PageUtils(planDOS, total);
        return pageUtil;
    }
    /**
     * 增加计划安排
     * @param plan
     * @return
     */
    @PostMapping()
    ResultBean save(@RequestBody PlanDO plan) {
        return ResultBean.operate(planService.save(plan) > 0);
    }

    /**
     * 修改计划安排
     * @param plan
     * @return
     */
    @PutMapping()
    public ResultBean update(@RequestBody PlanDO plan) {
        return ResultBean.operate(planService.update(plan) > 0);
    }


    /**
     * 删除计划安排
     * @param id
     * @return
     */
    @DeleteMapping()
    ResultBean remove( Long id) {
        return ResultBean.operate (planService.remove(id) > 0);
    }
}
