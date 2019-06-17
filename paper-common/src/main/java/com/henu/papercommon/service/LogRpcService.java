package com.henu.papercommon.service;

import com.henu.papercommon.dto.LogDO;
import com.henu.papercommon.intercepter.FeignIntercepter;
import com.henu.papercommon.utils.ResultBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    ResultBean save(LogDO logDO);
}
