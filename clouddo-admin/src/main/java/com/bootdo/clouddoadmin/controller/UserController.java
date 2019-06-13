package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.dto.MyUserDTO;
import com.bootdo.clouddoadmin.dto.UserDTO;
import com.bootdo.clouddoadmin.dto.UserRoleDTO;
import com.bootdo.clouddoadmin.dto.do2dto.UserConvert;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.LoginUserDTO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.clouddoadmin.utils.UserMOConvert;
/**
 * 用户信息
 * @author bootdo
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
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(userDTOS, total);
		return ResultBean.ok().put("page",pageUtil);
	}

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@PostMapping()
    ResultBean save(@RequestBody UserDTO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return ResultBean.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@PutMapping()
	public ResultBean update(@RequestBody UserDTO user) {
		return ResultBean.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping()
	ResultBean remove( Long id) {
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
