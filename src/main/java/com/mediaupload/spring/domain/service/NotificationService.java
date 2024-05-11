package com.mediaupload.spring.domain.service;

import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface NotificationService {

    void notify(NotificationRequestDTO data) throws ExecutionException, InterruptedException;
}
