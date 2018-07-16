package com.example.amineelouattar.starredrepos.models;

/**
 * Created by amineelouattar on 12/30/17.
 */

public class Repos {

    private String title, avatarUrl, username, description, rating;

    public Repos(String title, String avatarUrl, String username, String description, String rating) {
        this.title = title;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAvatarUrl() {
        return avatarUrl;
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
