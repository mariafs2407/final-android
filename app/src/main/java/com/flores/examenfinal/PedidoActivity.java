package com.flores.examenfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flores.examenfinal.adapter.PedidoAdapter;
import com.flores.examenfinal.adapter.ServicioenvioAdapter;
import com.flores.examenfinal.databinding.ActivityPedidoBinding;
import com.flores.examenfinal.databinding.ActivityServicioenvioBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidoActivity extends AppCompatActivity {
    private ActivityPedidoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pedido);
        binding = ActivityPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String idempresaenvio = bundle.getString("idempresaenvio");
        String nombre= bundle.getString("nombre");


        this.setTitle(nombre);
        leerServicio(idempresaenvio);
    }

    private void leerServicio(String idempresaenvio) {
        Log.d("DATOS", "response");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/pedidosenvio.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS", response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOS", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map= new HashMap<>();
                //ponemos caty.. porque en el php esta con ese nombre la variable idcategoria
                map.put("idempresaenvio",idempresaenvio);
                return  map;
            }
        } ;
        queue.add(stringRequest);
    }
    private void llenarLista(String response) {
        ArrayList arrayList = new ArrayList<HashMap<String,String>>();

        try {
            JSONArray jsonArray= new JSONArray(response);
            for (int i=0;i<jsonArray.length();i++){
                String idpedido = jsonArray.getJSONObject(i).getString("idpedido");
                String cliente =jsonArray.getJSONObject(i).getString("cliente");
                String paisdestinatario =jsonArray.getJSONObject(i).getString("paisdestinatario");
                String ciudaddestinatario=jsonArray.getJSONObject(i).getString("ciudaddestinatario");

                HashMap<String,String> map= new HashMap<>();
                map.put("idpedido",idpedido);
                map.put("cliente",cliente);
                map.put("paisdestinatario",paisdestinatario);
                map.put("ciudaddestinatario",ciudaddestinatario);
                arrayList.add(map);
            }
            PedidoAdapter pedidoAdapter = new PedidoAdapter(arrayList);
            binding.rvPedido.setAdapter(pedidoAdapter);
            binding.rvPedido.setLayoutManager(new GridLayoutManager(this,2));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}