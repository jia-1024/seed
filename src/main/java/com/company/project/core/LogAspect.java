package com.company.project.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author JHL
 * @version 1.0
 * @date 2023/4/21 15:35
 * @since : JDK 11
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.company.project.controller.*.*(..))")
    public void webLog() {
    }

    @Pointcut("@annotation(com.company.project.annotation.Log)")
    public void log() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("######################### \t[ 收到请求 ]\t #########################");
        logger.info("######################### \t[ URL : {} ]\t #########################", request.getRequestURL().toString());
        logger.info("######################### \t[ HTTP_METHOD : {} ]\t #########################", request.getMethod());
        logger.info("######################### \t[ IP : {} ]\t #########################", request.getRemoteAddr());
        logger.info("######################### \t[ CLASS_METHOD : {} ]\t #########################", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("######################### \t[ ARGS : {} ]\t #########################", Arrays.toString(joinPoint.getArgs()));
    }

    // @AfterReturning(returning = "ret", pointcut = "webLog()")
    // public void doAfterReturning(Object ret) {
    // }
    //
    // @AfterThrowing("webLog()")
    // public void throwss(JoinPoint jp) {
    // }
    //
    // @After("webLog()")
    // public void after(JoinPoint jp) {
    // }

    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object o = pjp.proceed();
            System.out.println("方法，结果是 :" + o);
            logger.info("######################### \t[ 方法签名为 【{}】 的返回结果是：{} ]\t #########################", pjp.getSignature().toString(), o.toString());

            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint point) {
        Object obj = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            obj = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return obj;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        System.out.println("######################### \t[ 方法待实现 ]\t #########################");
    }
}