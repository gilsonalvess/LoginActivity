package com.dwm.ufg.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dwm.ufg.service.ApiCliente;
import com.google.gson.Gson;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://private-c1bd8-gilsonalves.apiary-mock.com/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText mEmailView = (EditText) findViewById(R.id.email);
        final EditText mPasswordView = (EditText) findViewById(R.id.password);
        Button mBtnLogin = (Button) findViewById(R.id.email_sign_in_button);

        final Intent intent = new Intent(this, BemVindoActivity.class);
        final ApiCliente apiCliente = new ApiCliente(this, BASE_URL);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailView.getText().toString();
                String senha = mPasswordView.getText().toString();
                apiCliente.getDoInBackGround();
                String dadosLogin = getIntent().getStringExtra("jsonDadosLogin");
                UserLogin userLogin = trataJSON(dadosLogin);

                if (userLogin.getEmail().equals(email) && userLogin.getSenha().equals(senha)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email e/ou senha inválidos. Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private UserLogin trataJSON(String conteudo) {
        // parametrizar para Object facilita a reutilização
        if (conteudo != null) {
            final Gson gson = new Gson();
            return gson.fromJson(conteudo, UserLogin.class);
        }
        return null;
    }
}