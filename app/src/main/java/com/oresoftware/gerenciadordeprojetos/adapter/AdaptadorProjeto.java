package com.oresoftware.gerenciadordeprojetos.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.activities.ProjetosFragment;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProjeto extends RecyclerView.Adapter<AdaptadorProjeto.MyViewHolder> {

    private List<ProjetoModel> lista_projetos = new ArrayList<>();
    private final static String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private ProjetosFragment fragment;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refProjeto = database.getReference();

    public AdaptadorProjeto(List<ProjetoModel> projetos, ProjetosFragment fragment) {
        this.lista_projetos = projetos;
        this.fragment = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, categoria, orcamento, descricao;
        Button acessar, apagar;
        ProjetoModel projeto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.card_service_txt_title);
            categoria = itemView.findViewById(R.id.card_project_txt_categoriaT);
            orcamento = itemView.findViewById(R.id.card_project_txt_orcamentoT);
            descricao = itemView.findViewById(R.id.card_project_txt_descricaoT);
            acessar = itemView.findViewById(R.id.card_project_btn_acessar);
            apagar = itemView.findViewById(R.id.card_project_btn_apagar);

            apagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), "id " + projeto.getId(), Toast.LENGTH_SHORT).show();
                    projeto.remover();
                    lista_projetos.remove(projeto);
                    notifyDataSetChanged();



                }
            });


            acessar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    refProjeto.child("ref").child("projeto").setValue(projeto);
                    fragment.changeFragment();

                }
            });

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_project, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProjetoModel projeto = lista_projetos.get(position);

        holder.titulo.setText(projeto.getTitulo());
        holder.categoria.setText(projeto.getCategoria());
        String orcamento =  String.format("R$ %5.2f", projeto.getOrcamento());
        holder.orcamento.setText(orcamento);
        holder.descricao.setText(projeto.getDescricao());
        holder.projeto = projeto;

    }

    @Override
    public int getItemCount() {
        return lista_projetos.size();
    }


}
