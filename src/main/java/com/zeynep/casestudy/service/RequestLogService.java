package com.zeynep.casestudy.service;

import com.zeynep.casestudy.model.entity.RequestLog;

import java.util.List;

public interface RequestLogService {

    void save(RequestLog requestLog);

    List<RequestLog> getAll();
}
