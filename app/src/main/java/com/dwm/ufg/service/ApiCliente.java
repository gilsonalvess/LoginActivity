package com.dwm.ufg.service;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.Toast;

import com.dwm.ufg.loginactivity.BemVindoActivity;
import com.dwm.ufg.loginactivity.LoginActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiCliente extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String responseString;
    private String URL;

    public ApiCliente(Context context, String URL) {
        this.context = context;
        this.URL = URL;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (!isOnline(context)) {
            Toast.makeText(context, "Não foi possível realizar a conexão. Verifique sua internet", Toast.LENGTH_SHORT).show();
            responseString = null;
            return null;
        }
        doRegularCall();
        return null;
    }

    private void doRegularCall() {

        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(URL)
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
                responseString = responseBody.string();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("jsonDadosLogin", responseString);
            }
        });
    }

    private boolean isOnline(Context contextActivity) {
        ConnectivityManager cm = (ConnectivityManager) contextActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
    public void getDoInBackGround(){
        doInBackground();
    }
}