package com.zeynep.casestudy.controller;

import com.zeynep.casestudy.model.entity.RequestLog;
import com.zeynep.casestudy.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final RequestLogService requestLogService;

    @GetMapping("hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Here is your resource");
    }

    @GetMapping("all-requests")
    public ResponseEntity<List<RequestLog>> getAllRequestLogs(){
        return ResponseEntity.ok(requestLogService.getAll());
    }
}
