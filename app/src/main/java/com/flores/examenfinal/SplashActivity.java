package com.flores.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(() ->{
            mostrarBienvenido();} ,4000);
    }
    private void mostrarBienvenido() {
        startActivity(new Intent(this,BienvenidoActivity.class));
        finish();
    }
}