package com.mediaupload.spring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaFormat {

    private MediaEnum type;

    private String fileName;

    private UUID identificador;

    private double size;
}
