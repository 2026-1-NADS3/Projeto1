package com.example.appmaya3gl; // Mantenha a sua linha original aqui!

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView btnVoltar = findViewById(R.id.btn_voltar_perfil);
        TextView btnTrocarSenha = findViewById(R.id.btn_grafico_evolucao);
        MaterialButton btnHistorico = findViewById(R.id.btn_historico_perfil);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTrocarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, activityEvolucao.class);
                startActivity(intent);
            }
        });

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, ProntuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}