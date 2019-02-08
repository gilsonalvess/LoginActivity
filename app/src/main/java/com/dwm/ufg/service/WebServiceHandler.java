package com.dwm.ufg.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebServiceHandler {

    private static final int TIMEOUT_MILLIS = 1500;

    public boolean isOnline(Context contextActivity) {
        ConnectivityManager cm = (ConnectivityManager) contextActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public String getLogin() throws IOException {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            URL url = new URL("https://sandromoreira.docs.apiary.io/#");
            HttpURLConnection conn = null;
            StringBuilder sb;

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(TIMEOUT_MILLIS);
                conn.setReadTimeout(TIMEOUT_MILLIS);
                conn.connect();
                InputStream in = null;
                int status = conn.getResponseCode();

                if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    in = conn.getErrorStream();
                } else {
                    in = conn.getInputStream();
                }

                sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String linha = null;
                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }

                br.close();
                in.close();
            } catch (IOException e) {
                throw e;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return sb.toString();
        }

}
