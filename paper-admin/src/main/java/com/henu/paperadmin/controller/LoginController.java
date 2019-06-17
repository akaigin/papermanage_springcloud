package com.henu.paperadmin.controller;

import com.henu.paperadmin.service.AuthorityService;
import com.henu.paperadmin.service.TokenService;
import com.henu.paperadmin.service.UserService;
import com.henu.paperadmin.utils.SecuityUtils;
import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.utils.RedisUtil;
import com.henu.papercommon.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiger 1992lcg@163.com
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
