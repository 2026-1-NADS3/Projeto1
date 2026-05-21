package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityExercicios2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios2);

        TextView btnVoltar = findViewById(R.id.btn_voltar_ex2);
        MaterialButton btnConcluir = findViewById(R.id.btn_concluir_exercicio2);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Volta para a tela 1 //
            }
        });


        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vai da Tela 2 para a Tela 3 //
                Intent intent = new Intent(activityExercicios2.this, activityExercicios3.class);
                startActivity(intent);
            }
        });
    }
}