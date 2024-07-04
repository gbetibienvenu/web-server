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
        // Use "Lagos" as the location
        String location = "Lagos";
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weatherapi.com/v1/current.json?q=" + location + "&key=" + apiKey;

        Map<String, Object> response = new HashMap<>();
        try {
            Map weatherResponse = restTemplate.getForObject(url, Map.class);

            if (weatherResponse != null && weatherResponse.get("location") != null && weatherResponse.get("current") != null) {
                response.put("location", ((Map) weatherResponse.get("location")).get("name"));
                response.put("greeting", "Hello, " + visitorName + "! The temperature is " + ((Map) weatherResponse.get("current")).get("temp_c") + " degrees Celsius in " + ((Map) weatherResponse.get("location")).get("name"));
            } else {
                response.put("error", "Invalid response from weather API");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Failed to retrieve weather information");
            response.put("message", e.getMessage());
        }

        return response;
    }
}
