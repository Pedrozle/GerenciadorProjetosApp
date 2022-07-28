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

public class ProjetosActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos);


        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);


        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        intent = new Intent(ProjetosActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_projetos:
                        Toast.makeText(ProjetosActivity.this, "Projetos", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_create_project:
                        intent = new Intent(ProjetosActivity.this, NovoProjetoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_menu:
                        Toast.makeText(ProjetosActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;
            }
        });


    }



}