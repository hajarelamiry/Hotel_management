package com.project.HotelServer.Dto;

import lombok.Data;


public class RoomDto {
    private Long id;

    private String name;

    private String type;

    private Long price;

    private boolean available;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
