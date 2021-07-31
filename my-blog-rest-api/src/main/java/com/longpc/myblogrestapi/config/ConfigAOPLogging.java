package com.longpc.myblogrestapi.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ConfigAOPLogging {
    private Logger logger = LoggerFactory.getLogger(ConfigAOPLogging.class);

    @Before("execution(* com.longpc.myblogrestapi.resource.*.*(..))")
    public void beforeResource(JoinPoint joinPoint){
        logger.info(" --- called resource ---" + joinPoint.toString());
    }

    @Before("execution(* com.longpc.myblogrestapi.service.*.*(..))")
    public void beforeService(JoinPoint joinPoint){
        logger.info(" --- called service ---" + joinPoint.toString());
    }

}
