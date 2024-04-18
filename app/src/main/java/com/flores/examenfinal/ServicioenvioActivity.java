package com.flores.examenfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flores.examenfinal.adapter.ServicioenvioAdapter;
import com.flores.examenfinal.databinding.ActivityServicioenvioBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ServicioenvioActivity extends AppCompatActivity implements ServicioenvioAdapter.OnItemClickListener {
    private ActivityServicioenvioBinding binding;
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_servicioenvio);
        binding = ActivityServicioenvioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leerServicio();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //android.R.id.home es el boton que se crea por defecto para retroceder
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void leerServicio() {
        Log.d("DATOS", "response");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/servicioenvios.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void llenarLista(String response) {
        //ArrayList arrayList = new ArrayList<HashMap<String,String>>();
        try {
            JSONArray jsonArray= new JSONArray(response);
            for (int i=0;i<jsonArray.length();i++){
                String idempresaenvio = jsonArray.getJSONObject(i).getString("idempresaenvio");
                String nombre =jsonArray.getJSONObject(i).getString("nombre");
                String telefono =jsonArray.getJSONObject(i).getString("telefono");

                HashMap<String,String> map= new HashMap<>();
                map.put("idempresaenvio",idempresaenvio);
                map.put("nombre",nombre);
                map.put("telefono",telefono);
                arrayList.add(map);
            }
            ServicioenvioAdapter servicioenvioAdapter= new ServicioenvioAdapter(arrayList);
            binding.rvServicioEnvio.setAdapter(servicioenvioAdapter);
            binding.rvServicioEnvio.setLayoutManager(new LinearLayoutManager(this));
            servicioenvioAdapter.setOnItemClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(int position) {
        HashMap<String,String> map= (HashMap<String, String>) arrayList.get(position);
        String idempresaenvio=map.get("idempresaenvio");
        String nombre = map.get("nombre");
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("idempresaenvio",idempresaenvio);
        bundle.putString("nombre",nombre);

        Intent intent=new Intent(this,PedidoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}