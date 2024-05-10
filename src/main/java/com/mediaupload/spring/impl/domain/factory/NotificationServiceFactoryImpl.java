package com.mediaupload.spring.impl.domain.factory;

import com.mediaupload.spring.domain.factory.NotificationServiceFactory;
import com.mediaupload.spring.domain.model.StorageTypeEnum;
import com.mediaupload.spring.domain.service.NotificationService;
import com.mediaupload.spring.infra.notification.EmailNotificationService;
import com.mediaupload.spring.infra.notification.SMSNotificationService;
import org.springframework.stereotype.Component;


@Component
public class NotificationServiceFactoryImpl implements NotificationServiceFactory {


    @Override
    public NotificationService getNotificationService(boolean isEmailActive) {
        if (isEmailActive)
            return new EmailNotificationService();
        else
            return new SMSNotificationService();
    }
}
