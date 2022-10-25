package com.gitlab.pawelkorniak.module12;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("/info")
    public JSONObject get(){
        final String property = "property";
        String value = "value";
        return new JSONObject(Map.of(property, value));
    }
}
