package com.flores.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flores.examenfinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPedidos.setOnClickListener(this);
        binding.btnEnvios.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPedidos:
                mostrarPedidos();
                break;
            case R.id.btnEnvios:
                mostrarEnvio();
                break;
    }
}

    private void mostrarEnvio() {
        startActivity(new Intent( this,ServicioenvioActivity.class));
    }


    private void mostrarPedidos() {
        startActivity(new Intent(this,CajaActivity.class));
    }
}