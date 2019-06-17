package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.PlanDao;
import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;
import com.henu.paperadmin.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServinceImpl implements PlanService {
    @Autowired
    PlanDao planMapper;

    @Override
    public List<PlanDO> list(Map<String,Object> query, UserDO planDO){
        List<PlanDO> planDOS=planMapper.list(query,planDO);
        return planDOS;
    }

    @Override
    public int count(Map<String,Object> map){
        return planMapper.count(map);
    }
    @Override
    public int save(PlanDO plan) {
        int count = planMapper.insert(plan);
        return count;
    }

    @Override
    @CacheEvict(allEntries = true,beforeInvocation =true)
    public int update(PlanDO plan) {
        int r = planMapper.updateById(plan);
        return r;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int remove(Long planId) {
        return planMapper.deleteById(planId);
    }
}
