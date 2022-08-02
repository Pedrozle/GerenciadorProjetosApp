package com.oresoftware.gerenciadordeprojetos.adapter;

import android.annotation.SuppressLint;
import android.app.Service;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.oresoftware.gerenciadordeprojetos.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;
import com.oresoftware.gerenciadordeprojetos.models.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorService extends RecyclerView.Adapter<AdaptadorService.MyViewHolder> {

    private ProjetoModel projeto;
    private List<ServicesModel> list_services = new ArrayList<>();

    public AdaptadorService(List<ServicesModel> services, ProjetoModel projeto) {
        this.list_services = services;
        this.projeto = projeto;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView serv_titulo, serv_custo, serv_descricao;
        Button btn_apagar;
        ServicesModel service;
        int position;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serv_titulo = itemView.findViewById(R.id.card_service_txt_title);
            serv_custo = itemView.findViewById(R.id.card_service_txt_custoT);
            serv_descricao = itemView.findViewById(R.id.card_service_txt_descricaoT);
            btn_apagar = itemView.findViewById(R.id.card_service_btn_projeto_apagar);

            btn_apagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    projeto.removeService(service.getId());
                    list_services.remove(position);
                    Toast.makeText(itemView.getContext(), "Serviço removido com sucesso!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            });

        }
    }


    @NonNull
    @Override
    public AdaptadorService.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista  = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_services, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorService.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ServicesModel service = list_services.get(position);

        holder.serv_titulo.setText(service.getTitulo());
        holder.serv_descricao.setText(service.getDescricao());
        String custo = String.format("R$ %5.2f", service.getCusto());
        holder.serv_custo.setText(custo);
        holder.position = position;
        holder.service = service;

    }

    @Override
    public int getItemCount() {
        return list_services.size();
    }
}
