package com.oresoftware.gerenciadordeprojetos.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.adapter.AdaptadorProjeto;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;

import java.util.ArrayList;
import java.util.List;

public class ProjetosFragment extends Fragment {

    private RecyclerView rv_recicle;
    private List<ProjetoModel> lista_projetos = new ArrayList<>();
    private AdaptadorProjeto adaptador;

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("projetos");

    public ProjetosFragment() {
        // Required empty public constructor
    }

    public static ProjetosFragment newInstance(String param1, String param2) {
        ProjetosFragment fragment = new ProjetosFragment();
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

        View view = inflater.inflate(R.layout.fragment_projetos, container, false);

        adaptador = new AdaptadorProjeto(lista_projetos, ProjetosFragment.this);
        rv_recicle = view.findViewById(R.id.projetos_recycler);

        referencia.addValueEventListener(eventListener);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_recicle.setHasFixedSize(true);
        rv_recicle.setLayoutManager(layoutManager);
        rv_recicle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        rv_recicle.setAdapter(adaptador);

        return view;

    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){

                lista_projetos.clear();
                for(DataSnapshot snap: snapshot.getChildren()){
                    ProjetoModel projeto = snap.getValue(ProjetoModel.class);
                    lista_projetos.add(projeto);
                }
                adaptador.notifyDataSetChanged();

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    public void changeFragment(){
        ((MainActivity) getActivity()).replaceFragment(new ProjetoFragment());
    }

}