package com.bootdo.clouddoadmin.service;

import com.bootdo.clouddoadmin.domain.AuthorityMO;
import com.bootdo.clouddoadmin.domain.MenuDO;
import com.bootdo.clouddocommon.dto.RouterDTO;

import java.util.List;
import java.util.Set;

public interface AuthorityService {
    List<AuthorityMO> userMenus(Long userId);

    List<RouterDTO> RouterDTOsByUserId(Long userId);

    Set<String> getPermissionList(Long userId);
}
