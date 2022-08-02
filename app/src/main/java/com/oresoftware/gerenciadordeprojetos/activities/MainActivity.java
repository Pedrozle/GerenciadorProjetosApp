package com.oresoftware.gerenciadordeprojetos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new MainFragment());
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        replaceFragment(new MainFragment());
                        break;

                    case R.id.navigation_projetos:
                        replaceFragment(new ProjetosFragment());
                        break;

                    case R.id.navigation_create_project:
                        replaceFragment(new NovoProjetoFragment());
                        break;

                    case R.id.navigation_menu:
                        Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;
            }
        });


    }

    public void replaceFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fmt.replace(R.id.fragmentContainerView, fragment);
        fmt.commit();

    }

    public void replaceSelectedMenu(int id){

        switch (id) {

            case 1:
                navigationView.setSelectedItemId(R.id.navigation_home);
                break;

            case 2:
                navigationView.setSelectedItemId(R.id.navigation_projetos);
                break;

            case 3:
                navigationView.setSelectedItemId(R.id.navigation_create_project);
                break;


        }

    }



}