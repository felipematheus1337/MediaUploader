package com.mediaupload.spring.impl.domain.service;

import com.mediaupload.spring.application.amqp.MediaMessageProducer;
import com.mediaupload.spring.domain.exceptions.UserNotFoundException;
import com.mediaupload.spring.domain.factory.MediaFactory;
import com.mediaupload.spring.domain.model.MP3Media;
import com.mediaupload.spring.domain.model.Media;
import com.mediaupload.spring.domain.model.MediaFormat;
import com.mediaupload.spring.domain.repository.MediaRepository;
import com.mediaupload.spring.domain.service.FileService;
import com.mediaupload.spring.domain.service.MediaService;
import com.mediaupload.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final UserService userService;
    private final FileService fileService;

    private final MediaFactory mediaFactory;
    private final MediaMessageProducer mediaMessageProducer;




    @Override
    public void upload(byte[] archive ,Media media)  {
        log.info("Buscando tipo de mídia específico.");
        MediaFormat mediaFormat = mediaFactory.getMedia(media);

        log.info("Enviando mídia para a fila e em seguida fazer o upload!");
        this.mediaMessageProducer.publish(archive, mediaFormat);

    }

    public Media createAnMedia(MultipartFile file, Long id) throws IOException {

        if (!userService.existsUserById(id))
            throw new UserNotFoundException("Usuário não encontrado para fazer upload");

        Media media = new Media();

        media.setFileName(file.getOriginalFilename());
        media.setType(fileService.getMediaEnumByFile(file.getContentType()));
        media.setSize(file.getBytes().length);
        media.setIdentificador(UUID.randomUUID());

        return media;
    }
}
