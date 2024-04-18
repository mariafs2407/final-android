package com.flores.examenfinal.ui.main;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flores.examenfinal.R;
import com.flores.examenfinal.adapter.DatosAdapter;
import com.flores.examenfinal.databinding.FragmentDatosBinding;
import com.flores.examenfinal.datos.DatosSQLite;

import java.util.ArrayList;
import java.util.HashMap;


public class DatosFragment extends Fragment {
    private FragmentDatosBinding binding;
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();
    SwipeRefreshLayout swwipe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDatosBinding.inflate(inflater,container, false);
        View root = binding.getRoot();
        arrayList.clear();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swwipe = view.findViewById(R.id.swipe);

        swwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                leerDatos();
                swwipe.setRefreshing(false);
            }
        });

    }

    private void leerDatos() {
        DatosSQLite datosSQLite = new DatosSQLite(getActivity());
        Cursor cursor= datosSQLite.movimientosSelect(datosSQLite);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("idmovimiento",cursor.getString(cursor.getColumnIndexOrThrow("idmovimiento")));
                    map.put("pais",cursor.getString(cursor.getColumnIndexOrThrow("pais")));
                    map.put("poblacion",cursor.getString(cursor.getColumnIndexOrThrow("poblacion")));
                    map.put("area",cursor.getString(cursor.getColumnIndexOrThrow("area")));
                    map.put("capital",cursor.getString(cursor.getColumnIndexOrThrow("capital")));
                    arrayList.add(map);
                }while (cursor.moveToNext());
                DatosAdapter datosAdapter= new DatosAdapter(arrayList);
                binding.rvDatos.setAdapter(datosAdapter);
                binding.rvDatos.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvDatos.removeAllViewsInLayout();

            }
        }

    }
}