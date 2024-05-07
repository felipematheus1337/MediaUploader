package com.mediaupload.spring.impl.utils;

import com.mediaupload.spring.domain.model.MediaEnum;
import com.mediaupload.spring.domain.service.FileService;
import org.springframework.stereotype.Service;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public MediaEnum getMediaEnumByFile(String contentType) {

        switch (contentType) {

            case "audio/mpeg", "audio/mp3" -> {
                return MediaEnum.MP3;
            }

            case "video/mp4", "audio/mp4" -> {
                return MediaEnum.MP4;
            }

            default -> throw new UnsupportedOperationException("Mídia não suportada");
        }
    }
}
