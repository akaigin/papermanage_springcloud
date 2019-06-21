package com.henu.paperadmin.service.impl;


import com.henu.paperadmin.constants.Constants;
import com.henu.paperadmin.domain.*;

import com.henu.paperadmin.dto.UserDTO;
import com.henu.paperadmin.dao.UserDao;
import com.henu.paperadmin.dao.UserRoleDao;
import com.henu.paperadmin.service.UserService;
import com.henu.paperadmin.utils.BuildTree;
import com.henu.paperadmin.utils.MD5Utils;
import com.henu.paperadmin.utils.UserDOConvert;
import com.henu.paperadmin.vo.UserVO;
import com.henu.papercommon.dto.LoginUserDTO;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
@CacheConfig(cacheNames= Constants.USER_INFO)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userMapper;
	@Autowired
	UserRoleDao userRoleMapper;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
    @Cacheable(key="'currentUser'")
	public LoginUserDTO getCurrentUser(Long id) {
		LoginUserDTO user = userMapper.getCurrentUser(id);
		return user;
	}

	@Override
    @Cacheable(key = "'user_info_'+#user.id")
	public UserDO get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
		UserDO user = userMapper.get(id);
		//user.setroleIds(roleIds);
		return user;
	}

	@Override
	public List<UserMO> getUserList(Map<String,Object> map, Long roleId){
		return userMapper.getUserList(map,roleId);
	}

	@Override
	public List<UserDO> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	@Override
	public int countByRoleId(Map<String, Object> map,Long roleId) {
		return userMapper.countByRoleId(map,roleId);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Override
	public int save(UserDTO user) {
		int count = userMapper.save(UserDOConvert.userDTOToUserDO(user));
		Long userId = userMapper.getUserIdByUserName(user.getUsername());
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRoleDO ur = new UserRoleDO();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return count;
	}

	@Override
    @CacheEvict(allEntries = true,beforeInvocation =true)
	public int update(UserDTO user) {
		int r = userMapper.update(UserDOConvert.userDTOToUserDO(user));
		Long userId = user.getId();
		List<Long> roles = user.getRoleIds();
		if(0!=roles.size()){
			userRoleMapper.removeByUserId(userId);
			List<UserRoleDO> list = new ArrayList<>();
			for (Long roleId : roles) {
				UserRoleDO ur = new UserRoleDO();
				ur.setUserId(userId);
				ur.setRoleId(roleId);
				list.add(ur);
			}
			if (list.size() > 0) {
				userRoleMapper.batchSave(list);
			}
		}
		return r;
	}

	@Override
    @CacheEvict(allEntries = true)
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exits(Map<String, Object> params) {
		boolean exits = userMapper.list(params).size() > 0;
		return exits;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(UserVO userVO,UserDO userDO) throws Exception {
		if(Objects.equals(userVO.getUserDO().getId(),userDO.getId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
				return userMapper.update(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		UserDO userDO =get(userVO.getUserDO().getId());
		if("admin".equals(userDO.getUsername())){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userMapper.update(userDO);


	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}



	@Override
	public int updatePersonal(UserDO userDO) {
		return userMapper.update(userDO);
	}

	@Override
	public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
		return null;
	}

	@Override
    @CacheEvict(allEntries = true,beforeInvocation =true)
    public void cacheRemove(){}

}
