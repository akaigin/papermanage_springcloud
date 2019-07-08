package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.RoleDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;
import com.henu.paperadmin.service.PlanService;
import com.henu.paperadmin.utils.PlanDOConvert;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/plan")
@RestController
@Api("计划安排操作接口")
public class PlanController {
    @Autowired
    PlanService planService;

    //@PreAuthorize("hasAuthority('admin:plan:list')")
    @ApiOperation("获取计划安排列表并分页")
    //@Log("获取计划安排列表")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        String createUser= SecuityUtils.getCurrentUser().getName();
        PlanDO planDO=new PlanDO();
        List<PlanDTO> planDTOS = planService.list(query,planDO);
        Map<String,Object> map=new HashMap<>();
        int total = planService.count(map);
        PageUtils pageUtil = new PageUtils(planDTOS, total);
        return ResultBean.ok().put("page",pageUtil);
    }
    /**
     * 增加计划安排
     * @param planDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:plan:add')")
    @ApiOperation("增加计划安排")
    @Log("增加计划安排")
    @PostMapping()
    ResultBean save(@ApiParam(name="planDTO", value = "教学安排相关信息") @RequestBody PlanDTO planDTO) {

        String createUser=SecuityUtils.getCurrentUser().getName();
        planDTO.setCreateTime(new Date());
        planDTO.setCreateUser(createUser);
        return ResultBean.operate(planService.save(planDTO) > 0);
    }

    /**
     * 修改计划安排
     * @param planDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:plan:edit')")
    @ApiOperation("修改计划安排")
    @Log("修改计划安排")
    @PutMapping()
    public ResultBean update(@ApiParam(name="planDTO", value = "教学安排相关信息") @RequestBody PlanDTO planDTO) {
        planDTO.setModifyTime(new Date());
        return ResultBean.operate(planService.update(planDTO) > 0);
    }


    /**
     * 删除计划安排
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:plan:remove')")
    @ApiOperation("删除计划安排")
    @Log("删除计划安排")
    @DeleteMapping()
    ResultBean remove(@ApiParam(name="id", value = "计划ID") Long id) {
        return ResultBean.operate (planService.remove(id) > 0);
    }
}
