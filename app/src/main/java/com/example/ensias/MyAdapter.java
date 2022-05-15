package com.example.ensias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.Myviewholder>{
   Context context;
   ArrayList<professeurs> list;



    public MyAdapter(ArrayList<professeurs> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        professeurs pro=list.get(position);
        holder.fristname.setText(pro.getFirst());
        holder.lastname.setText(pro.getLast());
        holder.numero.setText(pro.getNumero());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder{

        TextView fristname;
        TextView lastname;
        TextView numero;

    public Myviewholder(@NonNull View itemView) {
        super(itemView);
        fristname=itemView.findViewById(R.id.first);
        lastname=itemView.findViewById(R.id.last);
        numero=itemView.findViewById(R.id.numero);
    }
}
}
