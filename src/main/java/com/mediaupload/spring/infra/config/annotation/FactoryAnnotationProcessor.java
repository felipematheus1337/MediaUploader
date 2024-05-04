package com.mediaupload.spring.infra.config.annotation;

import com.mediaupload.spring.domain.annotations.Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FactoryAnnotationProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        if (bean.getClass().isAnnotationPresent(Factory.class)) {
            log.info("Factory bean detected. @Factory " + beanName);

        }
        return bean;
    }
}
