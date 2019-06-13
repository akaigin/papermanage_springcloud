package com.bootdo.clouddoadmin.utils;
import java.util.List;
import com.bootdo.clouddoadmin.domain.UserDO;
import java.lang.Long;
import java.util.Date;
import com.bootdo.clouddoadmin.dto.UserDTO;
import java.lang.String;
import java.util.ArrayList;
/**
create by tiger's tool
*/
public class UserDOConvert {
public static UserDO userDTOToUserDO(UserDTO userDTO){
if(userDTO == null){
return null;
}
UserDO userDO = new UserDO();
userDO.setId(userDTO.getId());
userDO.setUsername(userDTO.getUsername());
userDO.setName(userDTO.getName());
userDO.setPassword(userDTO.getPassword());
userDO.setEmail(userDTO.getEmail());
userDO.setPhone(userDTO.getPhone());
userDO.setStatus(userDTO.getStatus());
userDO.setCreateTime(userDTO.getCreateTime());
userDO.setModifyTime(userDTO.getModifyTime());
userDO.setSex(userDTO.getSex());
return userDO;
}
public static UserDTO userDOToUserDTO(UserDO userDO){
if(userDO == null){
return null;
}
UserDTO userDTO = new UserDTO();
userDTO.setId(userDO.getId());
userDTO.setUsername(userDO.getUsername());
userDTO.setName(userDO.getName());
userDTO.setPassword(userDO.getPassword());
userDTO.setEmail(userDO.getEmail());
userDTO.setPhone(userDO.getPhone());
userDTO.setStatus(userDO.getStatus());
userDTO.setCreateTime(userDO.getCreateTime());
userDTO.setModifyTime(userDO.getModifyTime());
userDTO.setSex(userDO.getSex());
return userDTO;
}
public static List<UserDTO>userDOListToUserDTOList(List<UserDO> userDOList){
if(userDOList == null){
return null;
}
List<UserDTO>userDTOList = new ArrayList();
for(UserDO userDO : userDOList){
userDTOList.add(userDOToUserDTO(userDO));
}
return userDTOList;
}
public static List<UserDO>userDTOListToUserDOList(List<UserDTO> userDTOList){
if(userDTOList == null){
return null;
}
List<UserDO>userDOList = new ArrayList();
for(UserDTO userDTO : userDTOList){
userDOList.add(userDTOToUserDO(userDTO));
}
return userDOList;
}
}
