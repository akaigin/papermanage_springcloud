package com.henu.paperadmin.service.impl;

import com.henu.paperadmin.constants.Constants;
import com.henu.paperadmin.dao.AuthorityDao;
import com.henu.paperadmin.domain.AuthorityMO;
import com.henu.paperadmin.service.AuthorityService;
import com.henu.papercommon.dto.RouterDTO;
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

    @Override/*
    @Cacheable(key="'authorities_byUserId_'+#userId")*/
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
