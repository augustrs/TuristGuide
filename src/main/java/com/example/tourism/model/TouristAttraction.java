package com.example.tourism.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();

    public TouristAttraction(String name, String description, int id, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "TouristAttraction" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags;
    }
}