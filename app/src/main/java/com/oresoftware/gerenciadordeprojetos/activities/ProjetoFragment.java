package com.oresoftware.gerenciadordeprojetos.activities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.adapter.AdaptadorProjeto;
import com.oresoftware.gerenciadordeprojetos.adapter.AdaptadorService;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;
import com.oresoftware.gerenciadordeprojetos.models.ServicesModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjetoFragment extends Fragment {

    private TextView txt_title, txt_categoria, txt_orcamento, txt_disponivel, txt_descricao;
    private Button btn_editar, btn_adicionar_servico;
    private AdaptadorService adaptador;
    private List<ServicesModel> list_services = new ArrayList<>();
    private RecyclerView rv_recicle;
    private ProjetoModel projeto = new ProjetoModel();

    private final static String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refProjeto = database.getReference();

    public ProjetoFragment() {
        // Required empty public constructor
    }

    public static ProjetoFragment newInstance(String param1, String param2) {
        ProjetoFragment fragment = new ProjetoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_projeto, container, false);

        btn_editar = view.findViewById(R.id.projeto_btn_editar);
        btn_adicionar_servico = view.findViewById(R.id.projeto_btn_adicionar_servico);

        txt_title = view.findViewById(R.id.projeto_txt_title);
        txt_categoria = view.findViewById(R.id.card_details_txt_categoriaT);
        txt_orcamento = view.findViewById(R.id.card_details_txt_orcamentoT);
        txt_disponivel = view.findViewById(R.id.card_details_txt_disponívelT);
        txt_descricao = view.findViewById(R.id.card_details_txt_descricaoT);

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NovoProjetoFragment fragment = new NovoProjetoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable ("proj", projeto);
                fragment.setArguments(bundle);

                ((MainActivity) getActivity()).replaceFragment(fragment);

            }
        });

        btn_adicionar_servico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NovoServiceFragment fragment = new NovoServiceFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable ("proj", projeto);
                fragment.setArguments(bundle);

                ((MainActivity) getActivity()).replaceFragment(fragment);
            }
        });


        refProjeto.child("ref").child("projeto").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.i("aaaaaaaa", task.getResult().getValue().toString());
                projeto = task.getResult().getValue(ProjetoModel.class);
                refProjeto.child("services").child(projeto.getId()).addValueEventListener(eventListener);

                adaptador = new AdaptadorService(list_services, projeto);

                rv_recicle = view.findViewById(R.id.services_rv);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rv_recicle.setHasFixedSize(true);
                rv_recicle.setLayoutManager(layoutManager);
                rv_recicle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
                rv_recicle.setAdapter(adaptador);

                populateView(view);
            }
        });
        return view;

    }

    void populateView(View view){

        txt_title.setText(projeto.getTitulo());
        txt_categoria.setText(projeto.getCategoria());
        String orcamento = String.format("R$ %5.2f", projeto.getOrcamento());
        txt_orcamento.setText(orcamento);


        String disp = String.format("R$ %5.2f", (projeto.getOrcamento() - projeto.getCustoTotal()));
        txt_disponivel.setText(disp);
        txt_descricao.setText(projeto.getDescricao());

    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){

                list_services.clear();
                for(DataSnapshot snap: snapshot.getChildren()){
                    ServicesModel service = snap.getValue(ServicesModel.class);
                    list_services.add(service);
                }
                adaptador.notifyDataSetChanged();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}