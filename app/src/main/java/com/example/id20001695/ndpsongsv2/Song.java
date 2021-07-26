package com.example.id20001695.ndpsongsv2;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Song(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String asterisk = "*";
        if(stars == 2) {
            asterisk = "**";
        }
        else if (stars == 3) {
            asterisk = "***";
        }
        else if(stars == 4) {
            asterisk = "****";
        }
        else if (stars == 5){
            asterisk = "*****";
        }
        return asterisk;
    }
}

