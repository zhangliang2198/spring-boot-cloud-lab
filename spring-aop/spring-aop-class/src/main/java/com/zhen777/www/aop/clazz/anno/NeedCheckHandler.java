package com.zhen777.www.aop.clazz.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 需要检查处理程序.
 *
 * @author mikoozhang[zhangliang2198@outlook.com]
 * @version 1.0.0
 */
@Aspect
@Component
@Slf4j
public class NeedCheckHandler {
    @Resource
    private HttpServletRequest request;
    @Pointcut("@within(com.zhen777.www.aop.clazz.anno.NeedCheck) || @annotation(com.zhen777.www.aop.clazz.anno.NeedCheck)")
    public void needCheck() {
    }

    @Around(value = ("needCheck()"))
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String header = request.getHeader("shenyu-token-for-gate-way");
        String token = "test-token";
        if(!token.equals(header)){
            throw new Exception("请从网关访问");
        }
        return joinPoint.proceed();
    }

}
