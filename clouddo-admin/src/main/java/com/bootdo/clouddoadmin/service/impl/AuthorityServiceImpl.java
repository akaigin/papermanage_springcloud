package com.bootdo.clouddoadmin.service.impl;

import com.bootdo.clouddoadmin.constants.Constants;
import com.bootdo.clouddoadmin.dao.AuthorityDao;
import com.bootdo.clouddoadmin.domain.AuthorityMO;
import com.bootdo.clouddoadmin.domain.MenuDO;
import com.bootdo.clouddoadmin.service.AuthorityService;
import com.bootdo.clouddocommon.dto.RouterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = Constants.AUTHORITY_INFO)
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDao authorityDao;

    @Override
    public Set<String> getPermissionList(Long userId) {
        List<String> perms = authorityDao.getPermissionList(userId);
        return new HashSet<>(perms);
    }

    @Override
    public List<AuthorityMO> userMenus(Long userId) {
        return authorityDao.getMenuListByUserId(userId);
    }

    @Override
    @Cacheable(key="'authorities_byUserId_'+#userId")
    public List<RouterDTO> RouterDTOsByUserId(Long userId) {
        List<AuthorityMO> authorityMOS = userMenus(userId);
        List<RouterDTO> routerDTOs = new ArrayList<>();
        for (AuthorityMO authorityMO : authorityMOS) {
            RouterDTO routerDTO = new RouterDTO();
            routerDTO.setId(authorityMO.getAuthorityId());
            routerDTO.setParentId(authorityMO.getParentId());
            routerDTO.setPath(authorityMO.getUrl());
            routerDTO.setName(authorityMO.getName());
            routerDTO.setIconCls(authorityMO.getIcon());
            routerDTO.setMenuShow(true);
            routerDTO.setChildren(new ArrayList<>());
            routerDTO.setLeaf(null!=authorityMO.getType()&&authorityMO.getType()==1);
            routerDTOs.add(routerDTO);
        }
        return RouterDTO.buildList(routerDTOs, 0L);
    }
}
