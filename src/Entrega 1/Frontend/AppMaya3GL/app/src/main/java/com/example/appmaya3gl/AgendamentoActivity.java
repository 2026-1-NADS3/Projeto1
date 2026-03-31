package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AgendamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);
        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_agendamento);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostra uma mensagem na tela
                android.widget.Toast.makeText(AgendamentoActivity.this,
                        "Agendamento realizado com sucesso!",
                        android.widget.Toast.LENGTH_LONG).show();

                // Voltar para o Dashboard após agendar --
                finish();
            }
        });

    }
}