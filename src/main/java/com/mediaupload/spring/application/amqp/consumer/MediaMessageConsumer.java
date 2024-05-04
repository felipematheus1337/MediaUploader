package com.mediaupload.spring.application.amqp.consumer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaupload.spring.application.amqp.enums.TopicEnums;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import com.mediaupload.spring.domain.repository.MediaRepository;
import com.mediaupload.spring.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class MediaMessageConsumer {


    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = { "topic-aws", "topic-azure" } )
    public void listener(@Payload ConsumerRecord<String, Object> payload) throws IOException {

        var media = objectMapper.readValue(payload.value().toString(), MediaUploadDTO.class);

        if (Objects.equals(payload.topic(), TopicEnums.AWS.name())) this.sendToAws(media);

        else if(Objects.equals(payload.topic(), TopicEnums.AZURE.name())) sendToAzure(media);
    }

    private void sendToAws(MediaUploadDTO media) {
        // TO DO

    }

    private void sendToAzure(MediaUploadDTO media) {
        // TO DO

    }

}
