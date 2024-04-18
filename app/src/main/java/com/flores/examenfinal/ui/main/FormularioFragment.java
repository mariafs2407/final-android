package com.flores.examenfinal.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.flores.examenfinal.R;
import com.flores.examenfinal.datos.DatosSQLite;
import com.google.android.material.textfield.TextInputEditText;

public class FormularioFragment extends Fragment implements View.OnClickListener {
    TextInputEditText mtetPais,mtetpoblacion,mtetArea,mtetCapital;
    Button mbtnRegistrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtetPais= view.findViewById(R.id.tetPais);
        mtetpoblacion= view.findViewById(R.id.tetPoblacion);
        mtetArea=view.findViewById(R.id.tetArea);
        mtetCapital= view.findViewById(R.id.tetCapital);
        mbtnRegistrar= view.findViewById(R.id.btnRegistrarDatos);
        mbtnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String pais = mtetPais.getText().toString();
        int poblacion= Integer.parseInt(mtetpoblacion.getText().toString());
        float area= Float.parseFloat(mtetArea.getText().toString());
        String capital= mtetCapital.getText().toString();
        DatosSQLite datosSQLite= new DatosSQLite(getActivity());
        int autonumerico = datosSQLite.movimientosInsert(datosSQLite,pais,poblacion,area,capital);
        Toast.makeText(getActivity(), "Datos Ingresados" + autonumerico, Toast.LENGTH_SHORT).show();
    }
}