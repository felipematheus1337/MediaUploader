package com.mediaupload.spring.infra.notification;

import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import com.mediaupload.spring.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {


    private final JavaMailSender javaMailSender;

    @Override
    public void  notify(NotificationRequestDTO data) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(data.getEmail());
        message.setSubject("Upload of media - " + data.getIdentificador());
        message.setText("");

        javaMailSender.send(message);


    }
}
