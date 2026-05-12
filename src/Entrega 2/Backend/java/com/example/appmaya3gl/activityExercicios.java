package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast; // Importamos a ferramenta de mensagens
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton; // Importamos o botão

public class activityExercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        android.widget.TextView btnVoltar = findViewById(R.id.btn_voltar2);
        MaterialButton btnConcluir = findViewById(R.id.btn_concluir_exercicio);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityExercicios.this, activityExercicios2.class);
                startActivity(intent);

            }
        });
        btnVoltar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }
}