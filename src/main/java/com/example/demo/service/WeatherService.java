package com.example.demo.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

        @Value("${weather.api.key}")
        private String apiKey;

        public Map<String, Object> getGreeting(String visitorName) {
            String ip = "your_ip"; // Use a method to get the client IP
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weatherapi.com/v1/current.json?q=" + ip + "&key=" + apiKey;
            Map<String, Object> weatherResponse = restTemplate.getForObject(url, Map.class);

            Map<String, Object> response = new HashMap<>();
            response.put("client_ip", ip);
            response.put("location", ((Map) weatherResponse.get("location")).get("name"));
            response.put("greeting", "Hello, " + visitorName + "! The temperature is " + ((Map) weatherResponse.get("current")).get("temp_c") + " degrees Celsius in " + ((Map) weatherResponse.get("location")).get("name"));

            return response;
        }


}
