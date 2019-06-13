package com.bootdo.clouddocommon.service;

import com.bootdo.clouddocommon.dto.LogDO;
import com.bootdo.clouddocommon.intercepter.FeignIntercepter;
import com.bootdo.clouddocommon.utils.ResultBean;
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
