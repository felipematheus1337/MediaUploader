package com.mediaupload.spring.domain.service;


import com.mediaupload.spring.domain.model.MediaEnum;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    MediaEnum getMediaEnumByFile(String contentType);
}
