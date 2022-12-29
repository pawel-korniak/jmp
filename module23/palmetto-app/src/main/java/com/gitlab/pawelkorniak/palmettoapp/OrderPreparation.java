package com.gitlab.pawelkorniak.palmettoapp;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderPreparation {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "order", groupId = "group1")
    public void listenGroup(String message) throws JSONException {
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.getString("status").equals("new")) {
            System.out.println("Received Message by Palmetto-app in group order: " + message);
            jsonObject.put("status", "ready");
            kafkaTemplate.send("notification", jsonObject.toString());
            kafkaTemplate.send("notification-client", jsonObject.toString());
            System.out.println("Message sent: " + jsonObject.toString());
        }
    }

}
