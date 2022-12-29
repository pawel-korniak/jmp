package com.gitlab.pawelkorniak.courierapp;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.*;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDelivery {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "notification", groupId = "group1")
    public void listenGroupFoo(String message) throws ParseException, JSONException {
        System.out.println("Received Message by Courier-app in group order: " + message);
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.getString("status").equals("ready")) {
            jsonObject.put("status", "delivered");
            kafkaTemplate.send("notification-client", jsonObject.toString());
            System.out.println("message sent: "+jsonObject.toString());
        }
    }

}
