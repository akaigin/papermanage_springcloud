package com.henu.paperadmin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.henu.paperadmin.domain.PlanDO;
import com.henu.paperadmin.domain.ReportDO;
import com.henu.paperadmin.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportDao extends BaseMapper<ReportDO> {
    List<ReportDO> list(@Param("query") Map<String, Object> map, @Param("report") ReportDO reportDO);

    List<ReportDO> listCheck(@Param("query") Map<String, Object> map, @Param("report") ReportDTO reportDTO);

    int count(@Param("query") Map<String, Object> map, @Param("report") ReportDO reportDO);

    int countCheck(@Param("query") Map<String, Object> map, @Param("report") ReportDTO reportDTO);

    int save(ReportDO reportDO);

    int remove(Long reportId);

    int writeGuidance(String guidance, Long id, Long status);
    String getGuidance(Long id);
}
