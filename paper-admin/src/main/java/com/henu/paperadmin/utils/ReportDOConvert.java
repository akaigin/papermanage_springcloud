package com.henu.paperadmin.utils;
import java.util.List;
import java.lang.Long;
import java.util.Date;
import com.henu.paperadmin.dto.ReportDTO;
import java.lang.String;
import com.henu.paperadmin.domain.ReportDO;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class ReportDOConvert {
public static ReportDO reportDTOToReportDO(ReportDTO reportDTO){
if(reportDTO == null){
return null;
}
ReportDO reportDO = new ReportDO();
reportDO.setId(reportDTO.getId());
reportDO.setDescription(reportDTO.getDescription());
reportDO.setFileName(reportDTO.getFileName());
reportDO.setFilePath(reportDTO.getFilePath());
reportDO.setCreateTime(reportDTO.getCreateTime());
reportDO.setModifyTime(reportDTO.getModifyTime());
reportDO.setStatus(reportDTO.getStatus());
reportDO.setGuidance(reportDTO.getGuidance());
reportDO.setCreateUser(reportDTO.getCreateUser());
return reportDO;
}
public static ReportDTO reportDOToReportDTO(ReportDO reportDO){
if(reportDO == null){
return null;
}
ReportDTO reportDTO = new ReportDTO();
reportDTO.setId(reportDO.getId());
reportDTO.setDescription(reportDO.getDescription());
reportDTO.setFileName(reportDO.getFileName());
reportDTO.setFilePath(reportDO.getFilePath());
reportDTO.setCreateTime(reportDO.getCreateTime());
reportDTO.setModifyTime(reportDO.getModifyTime());
reportDTO.setStatus(reportDO.getStatus());
reportDTO.setGuidance(reportDO.getGuidance());
reportDTO.setCreateUser(reportDO.getCreateUser());
return reportDTO;
}
public static List<ReportDTO>reportDOListToReportDTOList(List<ReportDO> reportDOList){
if(reportDOList == null){
return null;
}
List<ReportDTO>reportDTOList = new ArrayList();
for(ReportDO reportDO : reportDOList){
reportDTOList.add(reportDOToReportDTO(reportDO));
}
return reportDTOList;
}
public static List<ReportDO>reportDTOListToReportDOList(List<ReportDTO> reportDTOList){
if(reportDTOList == null){
return null;
}
List<ReportDO>reportDOList = new ArrayList();
for(ReportDTO reportDTO : reportDTOList){
reportDOList.add(reportDTOToReportDO(reportDTO));
}
return reportDOList;
}
}
