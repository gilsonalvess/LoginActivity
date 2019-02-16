package com.dwm.ufg.service;

import com.dwm.ufg.loginactivity.UserLogin;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/login")
    Call<UserLogin> getUserLogin();

}

