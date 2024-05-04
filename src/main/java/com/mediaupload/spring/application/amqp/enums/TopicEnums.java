package com.mediaupload.spring.application.amqp.enums;

public enum TopicEnums {

    AWS("topic-aws"),
    AZURE("topic-azure");


    public String topicName;

    TopicEnums(String topicName) {
        this.topicName = topicName;
    }
}
