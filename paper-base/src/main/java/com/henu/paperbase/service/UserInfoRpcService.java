package com.henu.paperbase.service;

import com.henu.papercommon.config.FeignOauth2RequestInterceptor;
import com.henu.papercommon.dto.LogDO;
import com.henu.papercommon.utils.ResultBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignOauth2RequestInterceptor.class)
public interface UserInfoRpcService {

    @GetMapping("user/getName")
    String getName();
}
