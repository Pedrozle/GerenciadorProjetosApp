package com.oresoftware.gerenciadordeprojetos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.oresoftware.gerenciadordeprojetos.R;

public class MainActivity extends AppCompatActivity{

    private Button btn_create;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create = findViewById(R.id.main_btn_create);
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovoProjetoActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_projetos:
                        intent = new Intent(MainActivity.this, ProjetosActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_create_project:
                        intent = new Intent(MainActivity.this, NovoProjetoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_menu:
                        Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;
            }
        });


    }



}