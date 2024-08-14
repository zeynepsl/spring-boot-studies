package com.zeynep.casestudy.service.impl;

import com.zeynep.casestudy.model.entity.RequestLog;
import com.zeynep.casestudy.repository.RequestLogRepository;
import com.zeynep.casestudy.service.RequestLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestLogServiceImpl implements RequestLogService {
    private final RequestLogRepository repository;

    public void save(RequestLog requestLog) {
        repository.save(requestLog);
    }

    @Override
    public List<RequestLog> getAll() {
        return repository.findAll();
    }

}
