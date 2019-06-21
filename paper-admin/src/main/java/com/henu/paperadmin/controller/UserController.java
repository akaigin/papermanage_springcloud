package com.henu.paperadmin.controller;

import com.henu.paperadmin.domain.UserDO;
import com.henu.paperadmin.dto.UserDTO;
import com.henu.paperadmin.dto.do2dto.UserConvert;
import com.henu.paperadmin.service.RoleService;
import com.henu.paperadmin.service.UserService;
import com.henu.paperadmin.utils.MD5Utils;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.paperadmin.utils.UserMOConvert;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.dto.LoginUserDTO;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * @author tiger
 */
@RequestMapping("/user")
@RestController
@Api(value = "用户操作接口")
public class UserController extends BaseController {
    @Autowired
	UserService userService;
    @Autowired
	RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
	@ApiOperation("获取当前登录用户的相关信息")
	@Log("获取当前登录用户的相关信息")
    @GetMapping("/currentUser")
	public LoginUserDTO currentUser(){
		return userService.getCurrentUser(SecuityUtils.getCurrentUser().getId());
	}

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	@ApiOperation("根据id获取用户信息")
	@Log("根据id获取用户信息")
    @GetMapping("{id}")
	ResultBean get(@ApiParam(name="id",value = "所要搜索的用户id",required = true) @PathVariable("id") Long id ){
		UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
    	return ResultBean.ok().put("data",userDTO);
	}

	/**
	 * 分页查询教师
	 * @param params
	 * @return
	 */
	@ApiOperation("根据角色id获取用户列表")
	@Log("根据角色id获取用户列表")
	@GetMapping("/roleId/{roleId}")
	public ResultBean getTeacherList(@ApiParam(name="roleId",value = "所要搜索的角色id",required = true) @PathVariable Long roleId,
									 @ApiParam(name="params",value = "分页配置相关信息") @RequestParam Map<String, Object> params) {

		Query query = new Query(params);
		List<UserDTO> userDTOS = UserMOConvert.userMOListToUserDTOList((userService.getUserList(query,roleId)));
		//进行分页
		int total = userService.countByRoleId(query,roleId);
		PageUtils pageUtil = new PageUtils(userDTOS, total);
		return ResultBean.ok().put("page",pageUtil);
	}

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@ApiOperation("增加用户")
	@Log("增加用户")
	@PostMapping()
    ResultBean save(@ApiParam(name="user",value = "用户相关信息") @RequestBody UserDTO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		user.setCreateTime(new Date());
		user.setStatus("1");
		return ResultBean.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ApiOperation("修改用户")
	@Log("修改用户")
	@PutMapping()
	public ResultBean update(@ApiParam(name="user",value = "用户相关信息") @RequestBody UserDTO user) {
		user.setModifyTime(new Date());
		return ResultBean.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation("删除用户")
	@Log("删除用户")
	@DeleteMapping()
	ResultBean remove(@ApiParam(name="id",value = "用户ID") @RequestBody Long id) {
		return ResultBean.operate (userService.remove(id) > 0);
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	ResultBean batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return ResultBean.ok();
		}
		return ResultBean.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}

	@GetMapping("/tokenUser")
	public Principal user(Principal user){
		return user;
	}
}
