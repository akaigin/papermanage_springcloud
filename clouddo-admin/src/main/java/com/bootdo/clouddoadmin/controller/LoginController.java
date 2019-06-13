package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.constants.Constants;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.AuthorityService;
import com.bootdo.clouddoadmin.service.MenuService;
import com.bootdo.clouddoadmin.service.TokenService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.LoginDTO;
import com.bootdo.clouddocommon.dto.UserToken;
import com.bootdo.clouddocommon.utils.JwtUtils;
import com.bootdo.clouddocommon.utils.RedisUtil;
import com.bootdo.clouddocommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
@Api(value = "用户登录接口")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/router")
    @ApiOperation(value = "获取菜单信息")
    @Log("获取菜单信息")
    public ResultBean router() {
        return ResultBean.ok().put("router", authorityService.RouterDTOsByUserId(SecuityUtils.getCurrentUser().getId()));
    }

    @ApiOperation(value = "登出销户")
    @Log("登出销户")
    @RequestMapping("/logout")
    public ResultBean logout(@ApiParam("token") String token) {
        consumerTokenServices.revokeToken(token);
        userService.cacheRemove();
        return ResultBean.ok();
    }


}
