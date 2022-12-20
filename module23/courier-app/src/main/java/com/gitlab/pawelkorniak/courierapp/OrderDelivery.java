package com.gitlab.pawelkorniak.courierapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDelivery {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "notification", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message by Courier-app in group order: " + message);
        kafkaTemplate.send("notification", message + "delivered");
    }

}
