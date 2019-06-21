package com.henu.paperadmin.dao;


import com.henu.paperadmin.domain.PlanRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanRoleDao {
    int batchSave(List<PlanRoleDO> list);

    int batchUpdate(List<PlanRoleDO> list);
}
