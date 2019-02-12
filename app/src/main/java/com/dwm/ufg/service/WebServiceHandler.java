package com.dwm.ufg.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dwm.ufg.loginactivity.UserLogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WebServiceHandler {

    private static final String BASE_URL = "http://private-c1bd8-gilsonalves.apiary-mock.com/login";

    public boolean isOnline(Context contextActivity) {
        ConnectivityManager cm = (ConnectivityManager) contextActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public UserLogin obterDadosLogin() throws IOException {

        OkHttpClient httpClient = new OkHttpClient();
        final String[] resposta = new String[1];

        final Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }
                resposta[0] = responseBody.string();
            }
        });
        return jsonForObject(resposta[0]);
    }

    private  UserLogin jsonForObject(String userLoginJson) {
        Gson gson = new Gson();

        try {
            Type userLoginType = new TypeToken<UserLogin>() {}.getType();
            return gson.fromJson(userLoginJson, userLoginType);
        } catch (Exception e) {
            Log.e("Erro ao criar JSON", e.getMessage());
        }
        return null;
    }
}