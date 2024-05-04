package com.mediaupload.spring.infra.config.utils;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class UtilsConfigurationClass {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
