package com.dwm.ufg.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dwm.ufg.service.ApiCliente;
import com.dwm.ufg.service.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    protected ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mBtnLogin = (Button) findViewById(R.id.email_sign_in_button);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText mEmailView = (EditText) findViewById(R.id.email);
                final EditText mPasswordView = (EditText) findViewById(R.id.password);

                final String email = mEmailView.getText().toString();
                String senha = mPasswordView.getText().toString();

                validaLogin(email, senha);
            }
        });
    }

    private void validaLogin(final String email, final String senha){

        apiInterface = ApiCliente.getClient().create(ApiInterface.class);
        Call<UserLogin> call = apiInterface.getUserLogin();
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                final Intent intent = new Intent(getBaseContext(), BemVindoActivity.class);

                Log.d("TAG",response.code()+"");

                UserLogin userLogin = response.body();

                if (userLogin != null && userLogin.getEmail().equals(email) && userLogin.getSenha().equals(senha)) {
                    intent.putExtra("email", userLogin.getEmail());
                    intent.putExtra("img", R.drawable.user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email e/ou senha inválidos. Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final EditText mEmailView = (EditText) findViewById(R.id.email);
        final EditText mPasswordView = (EditText) findViewById(R.id.password);

        mEmailView.setText("");
        mPasswordView.setText("");
    }
}