package com.flores.examenfinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flores.examenfinal.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;

    public PedidoAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map=arrayList.get(position);
        holder.mtvidpedido.setText(map.get("idpedido"));
        holder.mtvcliente.setText(map.get("cliente"));
        holder.mtvpais.setText(map.get("paisdestinatario"));
        holder.mtvciudad.setText(map.get("ciudaddestinatario"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtvidpedido,mtvcliente,mtvpais,mtvciudad;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mtvidpedido=itemView.findViewById(R.id.tvIdPedido);
            mtvcliente=itemView.findViewById(R.id.tvCliente);
            mtvpais=itemView.findViewById(R.id.tvPais);
            mtvciudad=itemView.findViewById(R.id.tvCiudad);
        }
    }
}
