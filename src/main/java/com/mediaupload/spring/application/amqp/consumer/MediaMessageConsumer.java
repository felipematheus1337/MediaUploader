package com.mediaupload.spring.application.amqp.consumer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaupload.spring.application.amqp.enums.TopicEnums;
import com.mediaupload.spring.application.cloud.CloudUploadService;
import com.mediaupload.spring.application.factory.CloudUploadServiceFactory;
import com.mediaupload.spring.domain.factory.NotificationServiceFactory;
import com.mediaupload.spring.domain.model.MediaFormat;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import com.mediaupload.spring.domain.model.User;
import com.mediaupload.spring.domain.repository.MediaRepository;
import com.mediaupload.spring.domain.repository.UserRepository;
import com.mediaupload.spring.domain.service.NotificationService;
import com.mediaupload.spring.infra.adapter.MediaAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class MediaMessageConsumer {


    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final ObjectMapper objectMapper;

    private final CloudUploadServiceFactory cloudUploadServiceFactory;

    private final NotificationServiceFactory notificationServiceFactory;

    private final MediaAdapter mediaAdapter;


    @KafkaListener(topics = { "topic-aws", "topic-azure" } )
    public void listener(@Payload ConsumerRecord<String, Object> payload) throws IOException {

        var media = objectMapper.readValue(payload.value().toString(), MediaUploadDTO.class);

        if (Objects.equals(payload.topic(), TopicEnums.AWS.name())) this.sendToAws(media);

        else if(Objects.equals(payload.topic(), TopicEnums.AZURE.name())) sendToAzure(media);
    }

    private void sendToAws(MediaUploadDTO media) throws IOException {

        log.info("Fazendo upload para cloud");

        var cloudUploadService = cloudUploadServiceFactory.getService(media.getStorageTypeEnum());

        cloudUploadService.upload(media);

        var usuario = getUser(media.getMediaFormat().getIdentificador());

        NotificationService service = notificationServiceFactory.getNotificationService(usuario.isEmailActive());

       var notificationDTO = this.mediaAdapter.adaptMediaToNotificationDTO(usuario.getEmail() ,
               usuario.getPhoneNumber() , media);

       log.info("Enviando notificação.");

       service.notify(notificationDTO);

    }

    private User getUser(UUID identificador) {
        var media = mediaRepository.findByIdentificador(identificador);

        return media.getUser();
    }

    private void sendToAzure(MediaUploadDTO media) {
        // TO DO

    }

}
