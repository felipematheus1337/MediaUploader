package com.mediaupload.spring.impl.domain.service;

import com.mediaupload.spring.domain.exceptions.UserNotFoundException;
import com.mediaupload.spring.domain.factory.MediaFactory;
import com.mediaupload.spring.domain.model.Media;
import com.mediaupload.spring.domain.repository.MediaRepository;
import com.mediaupload.spring.domain.service.FileService;
import com.mediaupload.spring.domain.service.MediaService;
import com.mediaupload.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final UserService userService;
    private final FileService fileService;
    private final MediaFactory mediaFactory;


    @Value("${queue.aws}")
    private final String filaAWS;

    @Value("${queue.azure}")
    private final String filaAzure;

    @Override
    public void upload(byte[] archive ,Media media)  {

        // TRATAR ARQUIVO





       // TO DO UPLOAD AWS / AZURE


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
