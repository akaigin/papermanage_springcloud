package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.ReportDao;
import com.henu.paperadmin.dao.ReportRoleDao;
import com.henu.paperadmin.dao.RoleDao;
import com.henu.paperadmin.domain.ReportDO;
import com.henu.paperadmin.domain.ReportRoleDO;
import com.henu.paperadmin.dto.ReportDTO;
import com.henu.paperadmin.service.ReportService;
import com.henu.paperadmin.service.ReportService;
import com.henu.paperadmin.utils.ReportDOConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportMapper;

    @Autowired
    ReportRoleDao reportRoleMapper;

    @Autowired
    RoleDao roleMapper;

    @Override
    public List<ReportDTO> list(Map<String,Object> query, ReportDO reportDO){
        List<ReportDO> reportDOS=reportMapper.list(query,reportDO);
        List<ReportDTO> reportDTOS= new ArrayList<>();
        for(ReportDO reportDOResponse:reportDOS){
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(reportDOResponse.getId());
            reportDTO.setDescription(reportDOResponse.getDescription());
            reportDTO.setFileName(reportDOResponse.getFileName());
            reportDTO.setFilePath(reportDOResponse.getFilePath());
            reportDTO.setCreateTime(reportDOResponse.getCreateTime());
            reportDTO.setModifyTime(reportDOResponse.getModifyTime());
            reportDTO.setCreateUser(reportDOResponse.getCreateUser());
            reportDTO.setGuidance(reportDOResponse.getGuidance());
            reportDTO.setStatus(reportDOResponse.getStatus());
            List<Long> roleIds=reportRoleMapper.getRoleIdsByReportId(reportDOResponse.getId());
            String roleNames="";
            List<String> roleNamesResponse=roleMapper.getRoleNamesByRoleIds(roleIds);
            for(String roleNameResponse:roleNamesResponse){
                roleNames+=roleNameResponse+" ";
            }
            reportDTO.setRoleNames(roleNames);
            reportDTOS.add(reportDTO);
        }
        return reportDTOS;
    }

    @Override
    public int count(Map<String,Object> map){
        return reportMapper.count(map);
    }
    @Override
    public int save(ReportDTO report) {
        ReportDO reportDO= ReportDOConvert.reportDTOToReportDO(report);
        int count = reportMapper.save(reportDO);
        Long reportId = reportDO.getId();
        List<Long> roles = report.getRoleId();
        List<ReportRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            ReportRoleDO pr = new ReportRoleDO();
            pr.setReportId(reportId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            reportRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    //@CacheEvict(allEntries = true,beforeInvocation =true)
    public int update(ReportDTO report) {
        int r = reportMapper.updateById(ReportDOConvert.reportDTOToReportDO(report));
        Long reportId = report.getId();
        List<Long> roles = report.getRoleId();
        List<ReportRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            ReportRoleDO pr = new ReportRoleDO();
            pr.setReportId(reportId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            reportRoleMapper.batchUpdate(list);
        }
        return r;
    }

    @Override
    //@CacheEvict(allEntries = true)
    public int remove(Long reportId) {
        reportRoleMapper.remove(reportId);
        return reportMapper.remove(reportId);
    }

    @Override
    public int writeGuidance(String guidance, Long id, Long status){
        return reportMapper.writeGuidance(guidance,id, status);
    }

    @Override
    public String getGundance(Long id){
        return reportMapper.getGuidance(id);
    }
}
