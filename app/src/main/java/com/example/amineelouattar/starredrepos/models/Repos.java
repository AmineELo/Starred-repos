package com.example.amineelouattar.starredrepos.models;

/**
 * Created by amineelouattar on 12/30/17.
 */

public class Repos {

    private String title, avatar_url, username, description, rating;

    public Repos(String title, String avatar_url, String username, String description, String rating) {
        this.title = title;
        this.avatar_url = avatar_url;
        this.username = username;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }
}
