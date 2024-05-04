package com.mediaupload.spring.application.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaupload.spring.domain.exceptions.FileEmptyNotAllowedException;
import com.mediaupload.spring.domain.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("v1/media")
@RequiredArgsConstructor
@CrossOrigin("*")

public class MediaController {

    private final ObjectMapper objectMapper;

    private final MediaService mediaService;

    @PostMapping(path = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadMedia(@RequestPart("file")MultipartFile file,
                                              @RequestPart("userId") String userId) throws IOException {

        if (file.isEmpty()) throw new FileEmptyNotAllowedException("Arquivo não pode estar vazio");

        var idUser  = Long.parseLong(objectMapper.readValue("userId", String.class));

        var media = this.mediaService.createAnMedia(file, idUser);

        byte[] bytesArchive = file.getBytes();

        this.mediaService.upload(bytesArchive, media);

        return ResponseEntity.ok("Mensagem em breve.");
    }
}
