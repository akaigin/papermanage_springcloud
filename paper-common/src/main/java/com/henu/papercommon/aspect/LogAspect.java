package com.henu.papercommon.aspect;

import com.henu.papercommon.annotation.Log;
import com.henu.papercommon.context.FilterContextHandler;
import com.henu.papercommon.dto.LogDO;
import com.henu.papercommon.service.LogRpcService;
import com.henu.papercommon.utils.HttpContextUtils;
import com.henu.papercommon.utils.IPUtils;
import com.henu.papercommon.utils.JSONUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogRpcService logService;


    @Pointcut("@annotation(com.henu.papercommon.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setOperation(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        sysLog.setUserId(Long.parseLong(FilterContextHandler.getUserID() == null ? "000000" : FilterContextHandler.getUserID()));
        sysLog.setUsername(FilterContextHandler.getUsername() == null ? "" : FilterContextHandler.getUsername());
        /*sysLog.setUserId(SecuityUtils.getCurrentUser().getId());
        sysLog.setUsername(FilterContextHandler.getUsername() == null ? "" : FilterContextHandler.getUsername());*/
        sysLog.setTime((int) time);
        // 保存系统日志
        logService.save(sysLog);
    }
}

