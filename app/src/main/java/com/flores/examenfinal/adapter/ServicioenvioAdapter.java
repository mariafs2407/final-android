package com.flores.examenfinal.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flores.examenfinal.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ServicioenvioAdapter extends RecyclerView.Adapter<ServicioenvioAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;
    public static OnItemClickListener onItemClickListener;

    public ServicioenvioAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicioenvio,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map =arrayList.get(position);
        holder.mtvIdEmpEnvio.setText(map.get("idempresaenvio"));
        holder.mtvNombre.setText(map.get("nombre"));
        holder.mtvTelef.setText(map.get("telefono"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mtvIdEmpEnvio ,mtvNombre,mtvTelef;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvIdEmpEnvio=itemView.findViewById(R.id.tvIdEmEnvio);
            mtvNombre=itemView.findViewById(R.id.tvNombre);
            mtvTelef=itemView.findViewById(R.id.tvTelefono);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("POSICION",String.valueOf(getLayoutPosition()));
            onItemClickListener.onItemClick(getLayoutPosition());

        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        ServicioenvioAdapter.onItemClickListener = onItemClickListener;
    }
}
