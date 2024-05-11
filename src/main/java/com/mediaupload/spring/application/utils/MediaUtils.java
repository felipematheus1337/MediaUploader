package com.mediaupload.spring.application.utils;


import com.mediaupload.spring.domain.model.NotificationRequestDTO;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;



@Component
public class MediaUtils {



    public String generateMessageByNotificationData(NotificationRequestDTO data) {
        StringBuilder sb = new StringBuilder();

        String emailOrPhoneNumber = data.getPhoneNumber() != null && data.getPhoneNumber().length() > 0 ?
                data.getPhoneNumber() : data.getEmail();

        if (data.nomeArquivo != null && data.nomeArquivo.length() > 0) { sb.append("Upload Sucessfully"); }
        else sb.append("Failed to upload!");

        sb.append("Archive name: " + data.nomeArquivo);
        sb.append("Message sent to: " + emailOrPhoneNumber);

        var date = new LocalDate();
        sb.append(date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear());

        return sb.toString();


    }
}
