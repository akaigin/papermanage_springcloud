package demo.convert;
import java.util.List;
import java.lang.Long;
import java.util.Date;
import com.henu.paperadmin.domain.PlanDO;
import java.lang.String;
import com.henu.paperadmin.dto.PlanDTO;
import long;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class PlanDOConvert {
public static PlanDO planDTOToPlanDO(PlanDTO planDTO){
if(planDTO == null){
return null;
}
PlanDO planDO = new PlanDO();
planDO.setSerialVersionUID(planDTO.getSerialVersionUID());
planDO.setId(planDTO.getId());
planDO.setDescription(planDTO.getDescription());
planDO.setFileName(planDTO.getFileName());
planDO.setFilePath(planDTO.getFilePath());
planDO.setCreateTime(planDTO.getCreateTime());
planDO.setModifyTime(planDTO.getModifyTime());
planDO.setCreateUser(planDTO.getCreateUser());
return planDO;
}
public static PlanDTO planDOToPlanDTO(PlanDO planDO){
if(planDO == null){
return null;
}
PlanDTO planDTO = new PlanDTO();
planDTO.setSerialVersionUID(planDO.getSerialVersionUID());
planDTO.setId(planDO.getId());
planDTO.setDescription(planDO.getDescription());
planDTO.setFileName(planDO.getFileName());
planDTO.setFilePath(planDO.getFilePath());
planDTO.setCreateTime(planDO.getCreateTime());
planDTO.setModifyTime(planDO.getModifyTime());
planDTO.setCreateUser(planDO.getCreateUser());
return planDTO;
}
public static List<PlanDTO>planDOListToPlanDTOList(List<PlanDO> planDOList){
if(planDOList == null){
return null;
}
List<PlanDTO>planDTOList = new ArrayList();
for(PlanDO planDO : planDOList){
planDTOList.add(planDOToPlanDTO(planDO));
}
return planDTOList;
}
public static List<PlanDO>planDTOListToPlanDOList(List<PlanDTO> planDTOList){
if(planDTOList == null){
return null;
}
List<PlanDO>planDOList = new ArrayList();
for(PlanDTO planDTO : planDTOList){
planDOList.add(planDTOToPlanDO(planDTO));
}
return planDOList;
}
}
