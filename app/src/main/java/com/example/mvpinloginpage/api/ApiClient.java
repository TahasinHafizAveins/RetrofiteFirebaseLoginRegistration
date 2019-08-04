package com.example.mvpinloginpage.api;

import com.example.mvpinloginpage.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {


    @POST("/api/users/register")
    Call<User> addUser(@Body User user);

    @GET("/api/users/{email}")
    Call<User> getUser(@Path("email") String email);
}
