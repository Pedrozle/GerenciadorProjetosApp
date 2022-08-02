package com.oresoftware.gerenciadordeprojetos.models;

import static java.security.AccessController.getContext;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oresoftware.gerenciadordeprojetos.activities.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetoModel implements Serializable {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refProjeto = database.getReference();
    DatabaseReference refServico = database.getReference();

    private String id;
    private String titulo;
    private double orcamento;
    private String descricao;
    private String categoria;
    private double custoTotal;

    public ProjetoModel() {
    }

    public ProjetoModel(String id, String titulo, double orcamento, String descricao, String categoria, double custoTotal) {
        this.id = id;
        this.titulo = titulo;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.categoria = categoria;
        this.custoTotal = custoTotal;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public int addService(@NonNull ServicesModel service){

        if(service.getCusto() < (getOrcamento() - getCustoTotal())){
            this.custoTotal += service.getCusto();
            adicionarServico(service);
            return 1;
        }

        return -1;

    }

    public void removeService (String key){

        removerServico(key);

    }

    public void atualizar(){
        String key = getId();
        refProjeto.child("projetos").child(key).setValue(this);
    }

    public void salvar(){

        String key = refProjeto.child("projetos").push().getKey();
        setId(key);
        refProjeto.child("projetos").child(getId()).setValue(this);

    }

    public void remover(){

        refProjeto.child("projetos").child(getId()).removeValue();
        refProjeto.child("service").child(getId()).removeValue();

    }

    private void adicionarServico(ServicesModel servico){

        String key = refProjeto.child("services").push().getKey();
        servico.setId(key);
        refProjeto.child("services").child(getId()).child(key).setValue(servico);

    }

    private void removerServico(String key){

        refProjeto.child("services").child(getId()).child(key).removeValue();

    }




}
