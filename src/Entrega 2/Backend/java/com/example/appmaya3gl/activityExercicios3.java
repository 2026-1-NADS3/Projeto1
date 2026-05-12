package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityExercicios3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios3);

        TextView btnVoltar = findViewById(R.id.btn_voltar_ex3);
        MaterialButton btnConcluir = findViewById(R.id.btn_concluir_exercicio3);

        // Volta para a Tela 2 //
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Finaliza o treino e volta para a tela inicial //
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.SharedPreferences prefs = getSharedPreferences("MayaPrefs", MODE_PRIVATE);

                // Pesquisando sob a lógica da finalização do treino para a tela inicial //
                int progressoAtual = prefs.getInt("total_progress", 10);
                progressoAtual += 10;

                if (progressoAtual > 100) progressoAtual = 100;
                prefs.edit().putInt("total_progress", progressoAtual).apply();

                Toast.makeText(activityExercicios3.this, "Parabéns! Treino finalizado.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(activityExercicios3.this, DashboardActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
            }
        });
    }
}