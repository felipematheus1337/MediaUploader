package com.mediaupload.spring.domain.service;

import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void notify(NotificationRequestDTO data);
}
