package com.example.vendor.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @After("execution(* com.example.vendor.service.*.*(..))")
    public void log() {
        System.out.println("CUD operation executed");
    }
}