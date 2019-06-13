package com.bootdo.clouddoadmin.dao;

import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.domain.UserMO;
import com.bootdo.clouddocommon.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

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

	List<UserMO> getUserList(@Param("query") Map<String,Object> map, Long roleId);

	List<UserMO> getUser(Map<String, Object> map);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
