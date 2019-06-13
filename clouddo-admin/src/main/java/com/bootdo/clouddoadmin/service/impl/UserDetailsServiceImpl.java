package com.bootdo.clouddoadmin.service.impl;

import com.bootdo.clouddoadmin.dao.UserDao;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.domain.UserMO;
import com.bootdo.clouddoadmin.secuity.CurrentUser;
import com.bootdo.clouddoadmin.service.AuthorityService;
import com.bootdo.clouddoadmin.service.MenuService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddocommon.exception.CDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    MenuService menuService;
    @Autowired
    AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserMO> userList = userDao.getUser(new HashMap<String, Object>() {{
            put("username", username);
        }});
        if (userList.size() < 1) {
            throw new CDException("用户名或密码错误！");
        }
        UserMO userMO = userList.get(0);
        Set<String> perms = authorityService.getPermissionList(userMO.getId());
        Set<GrantedAuthority> authorities = perms.stream().filter(Objects::nonNull).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new CurrentUser(username, userMO.getPassword(), userMO.getId(), userMO.getName(),authorities);
    }

}
