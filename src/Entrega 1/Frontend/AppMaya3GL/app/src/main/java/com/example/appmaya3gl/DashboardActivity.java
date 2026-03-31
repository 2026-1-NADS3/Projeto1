package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.bottomnavigation.BottomNavigationView; // Adicionei essa importação

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Cartões no meio da tela
        MaterialCardView cardExercicios = findViewById(R.id.card_dicas);
        MaterialCardView cardProntuario = findViewById(R.id.card_prontuario);
        MaterialCardView cardAgendar = findViewById(R.id.card_agendar);

        // A Barra de Navegação Inferior (NAV)
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // 2. Cards (Programação)
        // Clique no cartão grande "Meus Exercícios"
        cardExercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExercicioCard = new Intent(DashboardActivity.this, activityExercicios.class);
                startActivity(intentExercicioCard);
            }
        });

        // Clique no cartão grande "Meu Prontuário"
        cardProntuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProntuarioCard = new Intent(DashboardActivity.this, ProntuarioActivity.class);
                startActivity(intentProntuarioCard);
            }
        });

        // Clique no cartão grande "Agendar Sessão"
        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgendarCard = new Intent(DashboardActivity.this, AgendamentoActivity.class);
                startActivity(intentAgendarCard);
            }
        });

        bottomNav.setOnItemSelectedListener(item -> {

            // Verifica se o ícone clicado foi o de "Exercícios"
            if (item.getItemId() == R.id.nav_exercicios) {
                Intent intentNavExercicios = new Intent(DashboardActivity.this, activityExercicios.class);
                startActivity(intentNavExercicios);
                return true; // Retorna true para avisar o Android que o clique foi processado
            }

            // Verifica se o ícone clicado foi o de "Prontuário"
            if (item.getItemId() == R.id.nav_historico) { // Verifique se o ID está certinho!
                Intent intentNavProntuario = new Intent(DashboardActivity.this, ProntuarioActivity.class);
                startActivity(intentNavProntuario);
                return true; // Retorna true para avisar o Android que o clique foi processado
            }
            return false; // Retorna false caso o ícone clicado não tenha uma ação programada
        });
    }
}