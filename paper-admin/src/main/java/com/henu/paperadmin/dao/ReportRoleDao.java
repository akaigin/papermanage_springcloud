package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.ReportRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportRoleDao {
    int batchSave(List<ReportRoleDO> list);

    int batchUpdate(List<ReportRoleDO> list);

    List<Long> getRoleIdsByReportId(Long reportId);

    int remove(Long reportId);
}
