package com.example.geektrust.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rider extends BaseEntity{
    private List<String> matches;
    public Rider(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.matches = new ArrayList<>();
    }

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        Objects.requireNonNull(matches);
        this.matches = matches;
    }
}