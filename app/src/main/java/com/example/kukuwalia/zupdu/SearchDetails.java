package com.example.kukuwalia.zupdu;

import java.io.Serializable;

public class SearchDetails implements Serializable {

    private int bed;
    private int bath;
    private int sqft;
    private String pets;
    private int rent;
    private String title;
    private String startDate;
    private String address;
    private String thumbnailUrl;

    public int getBed() {
        return bed;
    }
    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getBath() {
        return bath;
    }
    public void setBath(int bath) {
        this.bath = bath;
    }

    public int getSqft() {
        return sqft;
    }
    public void setSqft(int sqft) {
        this.sqft = sqft;
    }

    public String getPets() {
        return pets;
    }
    public void setPets(String pets) {
        this.pets = pets;
    }

    public int getRent() { return rent;    }
    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}