package com.bootdo.clouddoadmin.dao;

import com.bootdo.clouddoadmin.domain.AuthorityMO;
import com.bootdo.clouddoadmin.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityDao {
    List<AuthorityMO> getMenuListByUserId(Long id);

    List<String> getPermissionList(Long id);
}
