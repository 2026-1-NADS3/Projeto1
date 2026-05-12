package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityProntuarioAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prontuario_admin);

        // Vinculando botão de voltar
        TextView btnVoltar = findViewById(R.id.btn_voltar_admin_prontuarios);
        MaterialButton btnBaixar = findViewById(R.id.btn_baixar_relatorio);

        // Ação de Voltar para o Painel Admin
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Download (ainda não feito) //
        btnBaixar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityProntuarioAdmin.this, "Baixando relatório de Maria da Silva em PDF...", Toast.LENGTH_LONG).show();
            }
        });
    }
}