package com.dwm.ufg.service;

import com.dwm.ufg.loginactivity.UserLogin;

import java.util.List;

public interface ApiInterface {

        @GET("/login")
        Call<UserLogin> createUser(@Body User user);

    }
}
