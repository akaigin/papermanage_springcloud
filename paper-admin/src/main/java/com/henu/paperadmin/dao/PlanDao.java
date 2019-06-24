package com.henu.paperadmin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.PlanDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjinhu
 * @since 2019-06-16
 */
@Mapper
public interface PlanDao extends BaseMapper<PlanDO> {

    List<PlanDO> list(@Param("query") Map<String, Object> map,@Param("plan") PlanDO planDO);

    int count(Map<String,Object> map);

    int save(PlanDO planDO);

    int remove(Long planId);
}