package com.henu.paperadmin.dto.do2dto;


import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.UserDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    public UserDTO do2dto(UserDO person);

    public List<UserDTO> dos2dtos(List<UserDO> list);
}
