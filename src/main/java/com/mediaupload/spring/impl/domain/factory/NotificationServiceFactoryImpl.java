package com.mediaupload.spring.impl.domain.factory;

import com.amazonaws.services.sns.AmazonSNS;
import com.mediaupload.spring.application.utils.MediaUtils;
import com.mediaupload.spring.domain.factory.NotificationServiceFactory;
import com.mediaupload.spring.domain.model.StorageTypeEnum;
import com.mediaupload.spring.domain.service.NotificationService;
import com.mediaupload.spring.infra.notification.EmailNotificationService;
import com.mediaupload.spring.infra.notification.SMSNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NotificationServiceFactoryImpl implements NotificationServiceFactory {


    private final AmazonSNS amazonSNS;

    private final MediaUtils mediaUtils;

    private final JavaMailSender javaMailSender;


    @Override
    public NotificationService getNotificationService(boolean isEmailActive) {
        if (isEmailActive)
            return new EmailNotificationService(javaMailSender);
        else
            return new SMSNotificationService(amazonSNS, mediaUtils);
    }
}
