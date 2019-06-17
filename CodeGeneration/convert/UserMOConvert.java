package demo.convert;
import java.util.List;
import java.lang.Long;
import java.util.Date;
import com.henu.paperadmin.dto.UserDTO;
import java.lang.String;
import com.henu.paperadmin.domain.UserMO;
import long;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class UserMOConvert {
public static UserMO userDTOToUserMO(UserDTO userDTO){
if(userDTO == null){
return null;
}
UserMO userMO = new UserMO();
userMO.setSerialVersionUID(userDTO.getSerialVersionUID());
userMO.setId(userDTO.getId());
userMO.setUsername(userDTO.getUsername());
userMO.setName(userDTO.getName());
userMO.setPassword(userDTO.getPassword());
userMO.setEmail(userDTO.getEmail());
userMO.setPhone(userDTO.getPhone());
userMO.setStatus(userDTO.getStatus());
userMO.setCreateTime(userDTO.getCreateTime());
userMO.setModifyTime(userDTO.getModifyTime());
userMO.setSex(userDTO.getSex());
return userMO;
}
public static UserDTO userMOToUserDTO(UserMO userMO){
if(userMO == null){
return null;
}
UserDTO userDTO = new UserDTO();
userDTO.setSerialVersionUID(userMO.getSerialVersionUID());
userDTO.setId(userMO.getId());
userDTO.setUsername(userMO.getUsername());
userDTO.setName(userMO.getName());
userDTO.setPassword(userMO.getPassword());
userDTO.setEmail(userMO.getEmail());
userDTO.setPhone(userMO.getPhone());
userDTO.setStatus(userMO.getStatus());
userDTO.setCreateTime(userMO.getCreateTime());
userDTO.setModifyTime(userMO.getModifyTime());
userDTO.setSex(userMO.getSex());
return userDTO;
}
public static List<UserDTO>userMOListToUserDTOList(List<UserMO> userMOList){
if(userMOList == null){
return null;
}
List<UserDTO>userDTOList = new ArrayList();
for(UserMO userMO : userMOList){
userDTOList.add(userMOToUserDTO(userMO));
}
return userDTOList;
}
public static List<UserMO>userDTOListToUserMOList(List<UserDTO> userDTOList){
if(userDTOList == null){
return null;
}
List<UserMO>userMOList = new ArrayList();
for(UserDTO userDTO : userDTOList){
userMOList.add(userDTOToUserMO(userDTO));
}
return userMOList;
}
}
