package com.geektrust.entities;

import java.util.ArrayList;
import java.util.List;

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
        this.matches = matches;
    }
}