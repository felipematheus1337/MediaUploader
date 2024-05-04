package com.mediaupload.spring.application.amqp;


import com.mediaupload.spring.domain.exceptions.UnsuportedMediaContentTypeException;
import com.mediaupload.spring.domain.model.MP3Media;
import com.mediaupload.spring.domain.model.MP4Media;
import com.mediaupload.spring.domain.model.MediaFormat;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import com.mediaupload.spring.infra.adapter.MediaAdapter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaMessageProducer {


    @Value("${queue.aws}")
    private final String filaAWS;

    @Value("${queue.azure}")
    private final String filaAzure;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final MediaAdapter mediaAdapter;

    public void publish(byte[] archive, MediaFormat mediaFormat) {

        MediaUploadDTO mediaToUpload  = this.mediaAdapter.adaptMessageToProcessToDTO(archive, mediaFormat);

        var queueToSend = getQueueName(mediaFormat);

        ProducerRecord<String, Object> producerRecord;

        producerRecord = new ProducerRecord<>(queueToSend, mediaToUpload);

        kafkaTemplate.send(producerRecord);

    }


    private String getQueueName(MediaFormat mediaFormat) {

        if (mediaFormat instanceof MP3Media) return filaAWS;

        else if (mediaFormat instanceof MP4Media) return filaAzure;

        else throw new UnsuportedMediaContentTypeException("Queue for this media type not found");
    }


}
