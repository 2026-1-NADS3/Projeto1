package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class activityAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageView btnLogout = findViewById(R.id.iv_logout_admin);
        MaterialCardView btnProntuarios = findViewById(R.id.card_admin_prontuarios);
        MaterialCardView btnExercicios = findViewById(R.id.card_admin_exercicios);
        com.google.android.material.card.MaterialCardView btnAgenda = findViewById(R.id.card_admin_agenda);
        com.google.android.material.card.MaterialCardView btnComunicados = findViewById(R.id.card_admin_comunicados);

        // Sair do admin //
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityAdmin.this, activityLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Botões //
        btnProntuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityAdmin.this, activityProntuarioAdmin.class);
                startActivity(intent);
            }
        });


        btnExercicios.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.content.Intent intent = new android.content.Intent(activityAdmin.this, activityExerciciosAdmin.class);
                startActivity(intent);
            }
        });

        btnAgenda.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                // Vamos criar a tela activityAgendaAdmin no Passo 3!
                android.content.Intent intent = new android.content.Intent(activityAdmin.this, activityAgendaAdmin.class);
                startActivity(intent);
            }
        });

        btnComunicados.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                // Vamos criar a classe activityComunicadosAdmin no Passo 3!
                android.content.Intent intent = new android.content.Intent(activityAdmin.this, activityComunicadosAdmin.class);
                startActivity(intent);
            }
        });

    }
}