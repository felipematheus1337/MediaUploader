package com.mediaupload.spring.impl.domain.service;

import com.mediaupload.spring.domain.exceptions.FileEmptyNotAllowedException;
import com.mediaupload.spring.domain.model.Media;
import com.mediaupload.spring.domain.model.MediaEnum;
import com.mediaupload.spring.domain.repository.MediaRepository;
import com.mediaupload.spring.domain.service.FileService;
import com.mediaupload.spring.domain.service.MediaService;
import com.mediaupload.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void upload(byte[] archive ,Media media)  {

       


    }

    public Media createAnMedia(MultipartFile file, Long id) throws IOException {
        Media media = new Media();

        media.setFileName(file.getOriginalFilename());
        media.setType(fileService.getMediaEnumByFile(file.getContentType()));
        media.setSize(file.getBytes().length);
        media.setIdentificador(UUID.randomUUID());

        return media;
    }
}
