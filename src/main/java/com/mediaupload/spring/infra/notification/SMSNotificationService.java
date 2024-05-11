package com.mediaupload.spring.infra.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.mediaupload.spring.application.utils.MediaUtils;
import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import com.mediaupload.spring.domain.service.NotificationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
@Slf4j
public class SMSNotificationService implements NotificationService {

    private final AmazonSNS amazonSNS;

    private final MediaUtils mediaUtils;

    @Value("${sns-topicArn}")
    private String smsTopic;


    @Override
    public void notify(NotificationRequestDTO data) throws ExecutionException, InterruptedException {

        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(smsTopic)
                .withMessage(mediaUtils.generateMessageByNotificationData(data))
                .withPhoneNumber(data.getPhoneNumber());


        amazonSNS.publish(publishRequest);

        CompletableFuture<PublishResult> reqAsync =
                CompletableFuture
                        .supplyAsync(() -> amazonSNS.publish(publishRequest));


        var result = reqAsync.get();

        HttpStatus statusCode = HttpStatus.valueOf(result.getSdkHttpMetadata().getHttpStatusCode());

        log.info("Status code da requisição: " + statusCode);


    }
}
