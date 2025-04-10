package com.example.weatherforecast.model;

import java.util.List;

public class Daily {
    public List<String> time;
    public List<Double> temperature_2m_max;
    public List<Double> temperature_2m_min;
    public List<Integer> precipitation_probability_max;
    public List<Double> wind_speed_10m_max;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(List<Double> temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public List<Double> getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(List<Double> temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public List<Double> getWind_speed_10m_max() {
        return wind_speed_10m_max;
    }

    public void setWind_speed_10m_max(List<Double> wind_speed_10m_max) {
        this.wind_speed_10m_max = wind_speed_10m_max;
    }

    public List<Integer> getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(List<Integer> precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }
}
