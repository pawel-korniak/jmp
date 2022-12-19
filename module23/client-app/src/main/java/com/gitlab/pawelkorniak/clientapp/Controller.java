package com.gitlab.pawelkorniak.clientapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/new-order")
    public void saveOrder(@RequestBody Order order) {
        orderRepository.save(order);
        kafkaTemplate.send("order", "msg");
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderRepository.findById(id).get();
    }
}
