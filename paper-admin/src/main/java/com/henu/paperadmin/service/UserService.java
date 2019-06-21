package com.henu.paperadmin.service;

import com.henu.paperadmin.domain.Tree;
import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.domain.UserMO;
import com.henu.paperadmin.dto.UserDTO;
import com.henu.paperadmin.vo.UserVO;
import com.henu.papercommon.dto.LoginUserDTO;
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

	int countByRoleId(Map<String, Object> map,Long roleId);

	int count(Map<String, Object> map);

	int save(UserDTO user);

	int update(UserDTO user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exits(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;

	int adminResetPwd(UserVO userVO) throws Exception;

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
