package com.mediaupload.spring.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private UUID identificador;

    private String url;

    private double size;

    private MediaEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
