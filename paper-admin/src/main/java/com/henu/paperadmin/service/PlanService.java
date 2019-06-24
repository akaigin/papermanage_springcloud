package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;

import java.util.List;
import java.util.Map;

public interface PlanService {
    List<PlanDTO> list(Map<String,Object> query, PlanDO planDO);

    int count(Map<String,Object> map);

    int save(PlanDTO user);

    int update(PlanDTO user);

    int remove(Long userId);
    
}
