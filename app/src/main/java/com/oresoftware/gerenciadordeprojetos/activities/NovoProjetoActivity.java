package com.oresoftware.gerenciadordeprojetos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.oresoftware.gerenciadordeprojetos.R;

public class NovoProjetoActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_projeto);


        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);


        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        intent = new Intent(NovoProjetoActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_projetos:
                        intent = new Intent(NovoProjetoActivity.this, ProjetosActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_create_project:
                        Toast.makeText(NovoProjetoActivity.this, "Criar Projeto", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_menu:
                        Toast.makeText(NovoProjetoActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;
            }
        });


    }



}