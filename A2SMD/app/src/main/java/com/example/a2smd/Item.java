package com.example.a2smd;
public class Item {
    private String name;
    private String location;
    private String description;
    private String phone;
    private String rating;

    public Item(String name, String location, String description, String phone, String rating) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.phone = phone;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
