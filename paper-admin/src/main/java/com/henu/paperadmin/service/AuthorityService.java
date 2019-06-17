package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.AuthorityMO;
import com.henu.papercommon.dto.RouterDTO;

import java.util.List;
import java.util.Set;

public interface AuthorityService {
    List<AuthorityMO> userMenus(Long userId);

    List<RouterDTO> RouterDTOsByUserId(Long userId);

    Set<String> getPermissionList(Long userId);
}
