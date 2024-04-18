package com.flores.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flores.examenfinal.databinding.ActivityBienvenidoBinding;

public class BienvenidoActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBienvenidoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_bienvenido);
        binding = ActivityBienvenidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBienvenido.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}