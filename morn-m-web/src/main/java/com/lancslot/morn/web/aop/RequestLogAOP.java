package com.lancslot.morn.web.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.lancslot.morn.util.SnowFlake;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class RequestLogAOP {

    static Logger log = LoggerFactory.getLogger(RequestLogAOP.class);

    public RequestLogAOP() {

    }

    @Pointcut("execution(* com.lancslot.morn.web.controller..*(..))")
    public void controller() {
    }

//    @Before("controller()")
//    public void controller(JoinPoint point) {
//
//    }

    @Around("controller()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long uuid = SnowFlake.getInstance().getId();

        HttpServletRequest request = ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("[req]  uuid={}`path={}`http={}`method={}`req={}",
                uuid, request.getRequestURI(), request.getMethod(),
                getMethodInfo(point), JSONObject.toJSONString(request.getParameterMap()));

        long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();
        Object retVal = point.proceed(args);
        long endTime = System.currentTimeMillis();

        log.info("[resp] uuid={}`cost={}ms`resp={}", uuid, endTime - startTime, JSONObject.toJSONString(retVal));
        return retVal;
    }

    private String getMethodInfo(JoinPoint point) {
        return point.getTarget().getClass().getSimpleName() + "." + point.getSignature().getName();
    }
}
