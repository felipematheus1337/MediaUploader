package com.mediaupload.spring.domain.factory;


import com.mediaupload.spring.domain.annotations.Factory;
import com.mediaupload.spring.domain.model.Media;
import com.mediaupload.spring.domain.model.MediaFormat;

@Factory
public interface MediaFactory {

    MediaFormat getMedia(Media media);
}
