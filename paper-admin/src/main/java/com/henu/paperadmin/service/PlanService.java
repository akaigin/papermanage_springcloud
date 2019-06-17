package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;

import java.util.List;
import java.util.Map;

public interface PlanService {
    List<PlanDO> list(Map<String,Object> query, UserDO userDO);

    int count(Map<String,Object> map);

    int save(PlanDO user);

    int update(PlanDO user);

    int remove(Long userId);
    
}
