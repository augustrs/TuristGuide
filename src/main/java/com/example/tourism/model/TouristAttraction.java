package com.example.tourism.model;

public class TouristAttraction {
    private int id;
    private String name;
    private String description;

    public TouristAttraction(String name, String description, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
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

}
