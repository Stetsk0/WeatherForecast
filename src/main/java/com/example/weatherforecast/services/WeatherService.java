package com.example.weatherforecast.services;

import com.example.weatherforecast.model.Coordinates;
import com.example.weatherforecast.model.GeoResponse;
import com.example.weatherforecast.model.GeoResult;
import com.example.weatherforecast.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Coordinates getCoordinates(String city) {
        String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        GeoResponse response = restTemplate.getForObject(geoUrl, GeoResponse.class);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            GeoResult result = response.getResults().get(0);
            return new Coordinates(result.getLatitude(), result.getLongitude());
        } else {
            throw new RuntimeException("Місто не знайдено: " + city);
        }
    }

    public WeatherResponse getWeather(double latitude, double longitude, String type) {
        String weatherUrl;

        if ("daily".equalsIgnoreCase(type)) {
            weatherUrl = String.format(
                    Locale.ENGLISH,
                    "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=temperature_2m_max,temperature_2m_min,precipitation_probability_max,wind_speed_10m_max,relative_humidity_2m_max&timezone=auto",
                    latitude, longitude
            );
        } else {
            weatherUrl = String.format(
                    Locale.ENGLISH,
                    "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m&timezone=auto",
                    latitude, longitude
            );
        }

        return restTemplate.getForObject(weatherUrl, WeatherResponse.class);
    }
}