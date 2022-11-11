package com.example.simulacroexamen03.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulacroexamen03.R;
import com.example.simulacroexamen03.VerInfoActivity;
import com.example.simulacroexamen03.constantes.Constantes;
import com.example.simulacroexamen03.modelos.Partido;

import java.util.ArrayList;

public class partidoAdapter extends RecyclerView.Adapter<partidoAdapter.PartidoVH>{

    private Context context;
    private ArrayList<Partido> objects;
    private int resources;

    public partidoAdapter(Context context, ArrayList<Partido> objects, int cardLayout){
        this.context = context;
        this.objects = objects;
        this.resources = cardLayout;
    }

    @NonNull
    @Override
    public PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View PartidoView = LayoutInflater.from(context).inflate(resources, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        PartidoView.setLayoutParams(layoutParams);
        return new PartidoVH(PartidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoVH holder, int position) {
        Partido p = objects.get(position);
        holder.lblEquipo1.setText(p.getEquipo1());
        holder.lblEquipo2.setText(p.getEquipo2());
        holder.lblGoles1.setText(String.valueOf(p.getGoles1()));
        holder.lblGoles2.setText(String.valueOf(p.getGoles2()));

        holder.btnGanador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoPartido(decirGanador(p)).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VerInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARTIDO, p);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private android.app.AlertDialog dialogoPartido(String muchotexto){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        builder.setCancelable(true);
        TextView mensaje = new TextView(context);
        mensaje.setText(muchotexto);
        mensaje.setTextSize(20);
        mensaje.setTextColor(Color.BLACK);
        mensaje.setPadding(20,50,20,50);
        builder.setView(mensaje);

        return builder.create();
    }

    private String decirGanador(Partido p){
        if (p.getGoles1() > p.getGoles2()){
            return p.getEquipo1()+" ha ganado.";
        }
        else if (p.getGoles2() > p.getGoles1()){
            return p.getEquipo2()+" ha ganado.";
        }
        else {
            return "Ha sido un empate";
        }
    }

    public class PartidoVH extends RecyclerView.ViewHolder{

        TextView lblEquipo1, lblEquipo2, lblGoles1, lblGoles2;
        Button btnGanador;
        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lblEquipo1 = itemView.findViewById(R.id.lblEquipo1View);
            lblEquipo2 = itemView.findViewById(R.id.lblEquipo2View);
            lblGoles1 = itemView.findViewById(R.id.lblGoles1View);
            lblGoles2 = itemView.findViewById(R.id.lblGoles2View);
            btnGanador = itemView.findViewById(R.id.btnGanadorView);
        }
    }
}
