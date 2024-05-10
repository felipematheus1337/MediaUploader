package com.mediaupload.spring.infra.notification;

import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import com.mediaupload.spring.domain.service.NotificationService;
import org.springframework.stereotype.Service;


@Service
public class EmailNotificationService implements NotificationService {


    @Override
    public void notify(NotificationRequestDTO data) {

    }
}
