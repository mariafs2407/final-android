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

public class DatosAdapter extends RecyclerView.Adapter<DatosAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;

    public DatosAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_datos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> map =arrayList.get(position);
        holder.mtetpais.setText(map.get("pais"));
        holder.mtetpoblacion.setText(map.get("poblacion"));
        float area= Float.parseFloat(map.get("area"));
        holder.mtetarea.setText(String.format("%.2f",area));
        holder.mtetcapital.setText(map.get("capital"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtetpais,mtetpoblacion,mtetarea,mtetcapital;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtetpais = itemView.findViewById(R.id.tvpais);
            mtetpoblacion= itemView.findViewById(R.id.tvpoblacion);
            mtetarea= itemView.findViewById(R.id.tvarea);
            mtetcapital= itemView.findViewById(R.id.tvcapital);

        }
    }
}
