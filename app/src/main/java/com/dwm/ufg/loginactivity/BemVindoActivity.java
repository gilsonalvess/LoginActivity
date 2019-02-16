package com.dwm.ufg.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BemVindoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);
        Intent intent = getIntent();

        TextView textView = findViewById(R.id.id_tw_email);
        ImageView imageView = findViewById(R.id.id_img);

        textView.setText(intent.getStringExtra("email"));
        imageView.setImageResource(intent.getIntExtra("img", 0));

        Toast.makeText(this, "Seja Bem-Vindo(a)!", Toast.LENGTH_SHORT).show();
    }

    public void sair(View view) {
        finish();
    }
}
