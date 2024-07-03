package com.example.demo.controller;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class ApiController {

        @Autowired
        private WeatherService weatherService;

        @GetMapping
        public Map<String, Object> index() {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "API V1.0 currently running.");
            return response;
        }

        @GetMapping("/hello")
        public Map<String, Object> hello(@RequestParam(value = "visitor_name", defaultValue = "Guest") String visitorName) {
            return weatherService.getGreeting(visitorName);
        }

}
