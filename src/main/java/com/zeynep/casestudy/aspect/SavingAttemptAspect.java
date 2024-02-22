package com.zeynep.casestudy.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeynep.casestudy.model.entity.RequestLog;
import com.zeynep.casestudy.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class SavingAttemptAspect {

    private final RequestLogService requestLogService;
    private final ObjectMapper objectMapper;

    //some advice methods:
    @Before("allSigns()")
    public void saveLogsToDb(JoinPoint joinPoint) throws JsonProcessingException {
        System.out.println("this is my aspect that is executed before my all service methods and my all methods that start with sign");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = "system";
        if (auth.getPrincipal() instanceof String) {
            userName = (String) auth.getPrincipal();
        }

        RequestLog requestLog = new RequestLog();
        Object[] args = joinPoint.getArgs();
        requestLog.setSource(joinPoint.toString());//signature.getDeclaringType().getName()
        requestLog.setCreatedUser(userName);
        requestLog.setCreatedDate(LocalDateTime.now());
        requestLog.setRequest(objectMapper.writeValueAsString(args[0]));
        requestLogService.save(requestLog);
    }

    @Pointcut("execution(public * com.zeynep.casestudy.controller.AuthController.sign*(*))")
    public void allSigns(){}

}
