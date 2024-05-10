package com.mediaupload.spring.domain.repository;


import com.mediaupload.spring.domain.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {


    Media findByIdentificador(UUID uuid);
}
