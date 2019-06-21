package com.henu.paperadmin.dao;

import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.domain.UserMO;
import com.henu.papercommon.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao{

	LoginUserDTO getCurrentUser(Long userId);

	UserDO get(Long userId);

	List<UserMO> getUserList(@Param("query") Map<String,Object> map, @Param("roleId") Long roleId);

	List<UserMO> getUser(Map<String, Object> map);

	List<UserDO> list(Map<String, Object> map);

	int countByRoleId(Map<String,Object> map,Long roleId);

	Long getUserIdByUserName(String userName);

	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
