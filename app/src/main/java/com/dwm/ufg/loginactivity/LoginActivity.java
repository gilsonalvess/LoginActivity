package com.dwm.ufg.loginactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.dwm.ufg.service.WebServiceHandler;

import java.io.IOException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       EditText mEmailView = (EditText) findViewById(R.id.email);
       EditText mPasswordView = (EditText) findViewById(R.id.password);

        WebServiceHandler ws = new WebServiceHandler();

        try {
            ws.getLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

