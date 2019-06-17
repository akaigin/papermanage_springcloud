package com.henu.paperadmin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.UserDO;

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
public interface PlanDao extends BaseMapper<PlanDO> {
    List<PlanDO> list(Map<String, Object> map,UserDO userDO);

    int count(Map<String,Object> map);
}