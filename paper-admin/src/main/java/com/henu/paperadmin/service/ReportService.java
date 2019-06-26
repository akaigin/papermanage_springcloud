package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.ReportDO;
import com.henu.paperadmin.dto.ReportDTO;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<ReportDTO> list(Map<String,Object> query, ReportDO reportDO);

    int count(Map<String,Object> map);

    int save(ReportDTO user);

    int update(ReportDTO user);

    int remove(Long userId);

    int writeGuidance(String guidance, Long id, Long status);

    String getGundance(Long id);
}