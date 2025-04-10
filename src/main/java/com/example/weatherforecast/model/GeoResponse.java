package com.example.weatherforecast.model;

import java.util.List;

public class GeoResponse {
    private List<GeoResult> results;

    public List<GeoResult> getResults() {
        return results;
    }

    public void setResults(List<GeoResult> results) {
        this.results = results;
    }
}
