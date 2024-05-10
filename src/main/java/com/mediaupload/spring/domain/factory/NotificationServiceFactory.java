package com.mediaupload.spring.domain.factory;


import com.mediaupload.spring.domain.annotations.Factory;
import com.mediaupload.spring.domain.model.StorageTypeEnum;
import com.mediaupload.spring.domain.service.NotificationService;

@Factory
public interface NotificationServiceFactory {

    NotificationService getNotificationService(boolean isEmailActive);
}
