package com.gitlab.pawelkorniak.clientapp;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/new-order")
    public void saveOrder(@RequestBody Order order) throws JsonProcessingException {
        orderRepository.save(order);
        ObjectMapper mapper = new ObjectMapper();
        kafkaTemplate.send("order", mapper.writeValueAsString(order));
        System.out.println("sending message to topic: " + mapper.writeValueAsString(order));
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id) {

        return orderRepository.findById(id).get();
    }

    @KafkaListener(topics = "notification-client", groupId = "group1")
    public void listenGroupFoo(String message) throws JSONException {

        System.out.println("Received Message by Client-app in group order: " + message);

        Gson gson = new Gson();
        final Order order = gson.fromJson(message, Order.class);
        System.out.println("Updated ORDER : "+ orderRepository.save(order));
    }
}
