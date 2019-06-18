package com.example.githubapi.rest;

import com.example.githubapi.models.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndpoints {
    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
}
