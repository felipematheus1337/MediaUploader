package com.mediaupload.spring.impl.domain.factory;

import com.mediaupload.spring.domain.factory.MediaFactory;
import com.mediaupload.spring.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class MediaFactoryImpl implements MediaFactory {

    @Override
    public MediaFormat getMedia(Media media) {
        MediaEnum mediaEnum = media.getType();

        return switch (mediaEnum) {
            case MP3 -> MP3Media.builder()
                    .type(media.getType())
                    .size(media.getSize())
                    .identificador(media.getIdentificador())
                    .fileName(media.getFileName())
                    .build();
            case MP4 -> MP4Media.builder()
                    .type(media.getType())
                    .size(media.getSize())
                    .identificador(media.getIdentificador())
                    .fileName(media.getFileName())
                    .build();
            default -> throw new UnsupportedOperationException("Mídia não suportada");
        };
    }

}
