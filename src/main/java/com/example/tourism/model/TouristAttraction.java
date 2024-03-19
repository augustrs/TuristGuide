package com.example.tourism.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TouristAttraction {
    private String location;
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();
    private int id;
    Random random = new Random();

    public TouristAttraction(String name, String description, List<String> tags) {
        this.id = random.nextInt(10000) + 1;
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public TouristAttraction(int id, String name, String description, String location, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.tags = tags;
    }

    public static TouristAttraction createAttraction(int id, String name, String description, String location, List<String> tags) {
        return new TouristAttraction(id, name, description, location, tags);
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}