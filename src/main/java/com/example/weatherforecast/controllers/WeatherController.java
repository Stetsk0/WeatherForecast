package com.example.weatherforecast.controllers;

import com.example.weatherforecast.model.Coordinates;
import com.example.weatherforecast.model.WeatherResponse;
import com.example.weatherforecast.services.WeatherService;
import com.example.weatherforecast.util.ExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/weather";
    }

    @GetMapping("/weather")
    public String showWeather(@RequestParam(required = false) String city,
                              @RequestParam(defaultValue = "hourly") String type,
                              Model model) {
        if (city != null) {
            try {
                Coordinates coordinates = weatherService.getCoordinates(city);
                WeatherResponse weather = weatherService.getWeather(coordinates.lat(), coordinates.lon(), type);
                model.addAttribute("weather", weather);
            } catch (RuntimeException ex) {
                model.addAttribute("error", ex.getMessage());
            }

            model.addAttribute("city", city);
            model.addAttribute("type", type);
        }

        return "weather";
    }

    @GetMapping("/weather/export")
    public void exportExcel(@RequestParam String city,
                            @RequestParam(defaultValue = "hourly") String type,
                            HttpServletResponse response) throws IOException {
        Coordinates coordinates = weatherService.getCoordinates(city);
        WeatherResponse weather = weatherService.getWeather(coordinates.lat(), coordinates.lon(), type);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=weather_" + type + ".xlsx");

        new ExcelExporter().export(weather, type, response);
    }
}