package com.example.githubapi.models;

import com.google.gson.annotations.SerializedName;

public class GithubUser {
    @SerializedName("login")
    private String username;

    private String followers;
    private String following;
    private String name;
    private String email;

    @SerializedName("avatar_url")
    private String avatar;

    public GithubUser(String username, String followers, String following, String name, String email, String avatar) {
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
