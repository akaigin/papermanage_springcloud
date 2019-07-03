package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.ReportDO;
import com.henu.paperadmin.dto.GuidanceDTO;
import com.henu.paperadmin.dto.ReportDTO;
import com.henu.paperadmin.service.ReportService;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.paperadmin.utils.ToolUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@RestController
@Api("报告操作接口")
public class ReportController {
    @Autowired
    ReportService reportService;

    //@PreAuthorize("hasAuthority('admin:report:list')")
    @ApiOperation("获取报告列表并分页")
    @Log("获取报告列表")
    @GetMapping("/list")
    ResultBean list(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        String createUser= SecuityUtils.getCurrentUser().getName();
        ReportDO reportDO=new ReportDO();
        reportDO.setCreateUser(createUser);
        List<ReportDTO> reportDTOS = reportService.list(query,reportDO);
        Map<String,Object> map=new HashMap<>();
        map.put("createUser",createUser);
        int total = reportService.count(query,reportDO);
        PageUtils pageUtil = new PageUtils(reportDTOS, total);
        return ResultBean.ok().put("page",pageUtil);
    }

    //@PreAuthorize("hasAuthority('admin:report:list')")
    @ApiOperation("获取报告列表并分页")
    @Log("获取报告列表")
    @GetMapping("/listCheck")
    ResultBean listCheck(@ApiParam(name="params", value = "分页配置相关信息") @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        Long tutorId= SecuityUtils.getCurrentUser().getId();
        ReportDTO reportDTO=new ReportDTO();
        reportDTO.setTutorId(tutorId);
        List<ReportDTO> reportDTOS = reportService.listCheck(query,reportDTO);
        int total = reportService.countCheck(query, reportDTO);
        PageUtils pageUtil = new PageUtils(reportDTOS, total);
        return ResultBean.ok().put("page",pageUtil);
    }
    /**
     * 增加报告
     * @param reportDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:report:add')")
    @ApiOperation("增加报告")
    @Log("增加报告")
    @PostMapping()
    ResultBean save(@ApiParam(name="reportDTO", value = "教学相关信息") @RequestBody ReportDTO reportDTO) {

        String createUser=SecuityUtils.getCurrentUser().getName();
        reportDTO.setCreateTime(new Date());
        reportDTO.setCreateUser(createUser);
        reportDTO.setStatus(ToolUtils.intToLong(3));
        return ResultBean.operate(reportService.save(reportDTO) > 0);
    }

    /**
     * 修改报告
     * @param reportDTO
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:report:edit')")
    @ApiOperation("修改报告")
    @Log("修改报告")
    @PutMapping()
    public ResultBean update(@ApiParam(name="reportDTO", value = "教学相关信息") @RequestBody ReportDTO reportDTO) {
        reportDTO.setModifyTime(new Date());
        return ResultBean.operate(reportService.update(reportDTO) > 0);
    }


    /**
     * 删除报告
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:report:remove')")
    @ApiOperation("删除报告")
    @Log("删除报告")
    @DeleteMapping()
    ResultBean remove(@ApiParam(name="id", value = "报告ID") Long id) {
        return ResultBean.operate (reportService.remove(id) > 0);
    }

    /**
     * 发表指导意见
     * @param
     * @return
     */
    //@PreAuthorize("hasAuthority('admin:report:check')")
    @ApiOperation("发表指导意见")
    @Log("发表指导意见")
    @PostMapping("guidance")
    ResultBean writeGuidance(@ApiParam(name="guidance", value = "指导意见") @RequestBody GuidanceDTO guidanceDTO) {
        return ResultBean.operate (reportService.writeGuidance(guidanceDTO.getGuidance(),guidanceDTO.getId(),guidanceDTO.getStatus()) > 0);
    }

    /**
     * 查看指导意见
     * @param
     * @return
     */
    @ApiOperation("查看指导意见")
    @Log("查看指导意见")
    @GetMapping("guidance")
    ResultBean getGuidance(@ApiParam(name="id", value = "报告ID") Long id) {
        return ResultBean.ok().put ("guidance",reportService.getGundance(id) );
    }
}
