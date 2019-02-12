package com.dwm.ufg.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dwm.ufg.service.SessionHandler;
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

        final EditText mEmailView = (EditText) findViewById(R.id.email);
        final EditText mPasswordView = (EditText) findViewById(R.id.password);
        Button mBtnLogin = (Button) findViewById(R.id.email_sign_in_button);

        final Intent intent = new Intent(this, BemVindoActivity.class);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String senha = mPasswordView.getText().toString();

                Boolean isLoginValido = false;
                try {
                    isLoginValido = validaLogin(email, senha);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (isLoginValido) {
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email e/ou senha inválidos. Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Boolean validaLogin(String email, String senha) throws IOException {
        WebServiceHandler webServiceHandler = new WebServiceHandler();
        SessionHandler sessionHandler = new SessionHandler();

        UserLogin userLogin = webServiceHandler.obterDadosLogin();

        if(userLogin != null){
            return userLogin.getEmail().equals(email) && userLogin.getSenha().equals(senha);
        }
        return false;
    }
}