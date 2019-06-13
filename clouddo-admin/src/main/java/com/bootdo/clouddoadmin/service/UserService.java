package com.bootdo.clouddoadmin.service;

import com.bootdo.clouddoadmin.domain.Tree;
import com.bootdo.clouddoadmin.domain.DeptDO;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.domain.UserMO;
import com.bootdo.clouddoadmin.dto.UserDTO;
import com.bootdo.clouddoadmin.vo.UserVO;
import com.bootdo.clouddocommon.dto.LoginUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
	LoginUserDTO getCurrentUser(Long id);

	UserDO get(Long id);

	List<UserMO> getUserList(Map<String,Object> map,Long roleId);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDTO user);

	int update(UserDTO user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exits(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;

    void cacheRemove();
}
