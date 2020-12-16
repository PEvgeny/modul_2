package com.example.restservice;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.restservice.GreetingController)")
    public void stringProcessingMethods() {
    };

    @After("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
            .getName();
        String params = Arrays.toString(jp.getArgs());
        logger.log(Level.INFO, "method name: " + methodName + " params: " + params);
    }

    @AfterReturning(pointcut = "execution(public * com.example.restservice.GreetingController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.log(Level.INFO, "return value: id = " + ((Greeting)result).getId() + ", content = " + ((Greeting)result).getContent());
    }
}

