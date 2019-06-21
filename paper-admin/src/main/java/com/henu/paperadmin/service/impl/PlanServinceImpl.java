package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.dao.PlanDao;
import com.henu.paperadmin.dao.PlanRoleDao;
import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.PlanRoleDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.domain.UserRoleDO;
import com.henu.paperadmin.dto.PlanDTO;
import com.henu.paperadmin.service.PlanService;
import com.henu.paperadmin.utils.PlanDOConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlanServinceImpl implements PlanService {
    @Autowired
    PlanDao planMapper;

    @Autowired
    PlanRoleDao planRoleMapper;
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
    public int save(PlanDTO plan) {
        int count = planMapper.insert(PlanDOConvert.planDTOToPlanDO(plan));
        Long planId = plan.getId();
        List<Long> roles = plan.getRoleId();
        List<PlanRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            PlanRoleDO pr = new PlanRoleDO();
            pr.setPlanId(planId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            planRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    @CacheEvict(allEntries = true,beforeInvocation =true)
    public int update(PlanDTO plan) {
        int r = planMapper.updateById(PlanDOConvert.planDTOToPlanDO(plan));
        Long planId = plan.getId();
        List<Long> roles = plan.getRoleId();
        List<PlanRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            PlanRoleDO pr = new PlanRoleDO();
            pr.setPlanId(planId);
            pr.setRoleId(roleId);
            list.add(pr);
        }
        if (list.size() > 0) {
            planRoleMapper.batchUpdate(list);
        }
        return r;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int remove(Long planId) {
        return planMapper.deleteById(planId);
    }
}
