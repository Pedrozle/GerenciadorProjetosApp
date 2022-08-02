package com.oresoftware.gerenciadordeprojetos.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServicesModel {

    private String id;
    private String titulo;
    private double custo;
    private String descricao;

    public ServicesModel() {

    }

    public ServicesModel(String id, String titulo, double custo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.custo = custo;
        this.descricao = descricao;
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

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
