package com.henu.papercommon.service;

import com.henu.papercommon.config.FeignOauth2RequestInterceptor;
import com.henu.papercommon.dto.LogDO;
import com.henu.papercommon.intercepter.FeignIntercepter;
import com.henu.papercommon.utils.ResultBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignOauth2RequestInterceptor.class)
public interface LogRpcService {

    @PostMapping("log/save")
    ResultBean save(LogDO logDO);
}
