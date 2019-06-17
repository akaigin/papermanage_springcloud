package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.AuthorityMO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityDao {
    List<AuthorityMO> getMenuListByUserId(Long id);

    List<String> getPermissionList(Long id);
}
