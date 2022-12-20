package com.gitlab.pawelkorniak.clientapp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controller {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/new-order")
    public void saveOrder(@RequestBody Order order) {
        orderRepository.save(order);
        System.out.println("sending message to topic");
        kafkaTemplate.send("order", order.toString());
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderRepository.findById(id).get();
    }

//    @KafkaListener(topics = "order", groupId = "foo")
//    public void listenGroupFoo(String message) {
//        System.out.println("Received Message in group order: " + message);
//    }
}
