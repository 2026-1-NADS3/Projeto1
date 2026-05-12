package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import com.google.android.material.chip.Chip;

public class AgendamentoActivity extends AppCompatActivity {

    // Variável para guardar a hora que o paciente tocar //
    private String horarioSelecionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        com.google.android.material.button.MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_agendamento);
        android.widget.TextView btnVoltar = findViewById(R.id.btn_voltar);

        // Chip (Horários disponíveis, programados no xml) //
        Chip chip0800 = findViewById(R.id.chip_0800);
        Chip chip0900 = findViewById(R.id.chip_0900);
        Chip chip1030 = findViewById(R.id.chip_1030);
        Chip chip1400 = findViewById(R.id.chip_1400);
        Chip chip1530 = findViewById(R.id.chip_1530);
        Chip chip1700 = findViewById(R.id.chip_1700);

        if (chip0800 != null) {
            chip0800.setOnClickListener(v -> horarioSelecionado = "08:00");
        }

        if (chip0900 != null) {
            chip0900.setOnClickListener(v -> horarioSelecionado = "09:00");
        }

        if (chip1030 != null) {
            chip1030.setOnClickListener(v -> horarioSelecionado = "10:30");
        }

        if (chip1400 != null) {
            chip1400.setOnClickListener(v -> horarioSelecionado = "14:00");
        }

        if (chip1530 != null) {
            chip1530.setOnClickListener(v -> horarioSelecionado = "15:30");
        }

        if (chip1700 != null) {
            chip1700.setOnClickListener(v -> horarioSelecionado = "17:00");
        }

        btnVoltar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent intent = new Intent(AgendamentoActivity.this, DashboardActivity.class);

                // Flags garante a não duplicidade //
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                finish();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Avisando para seleção de horários //
                if (horarioSelecionado.isEmpty()) {
                    android.widget.Toast.makeText(AgendamentoActivity.this,
                            "Por favor, selecione um horário primeiro.",
                            android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AgendamentoActivity.this, activityConfirmacaoAgendamento.class);

                intent.putExtra("HORARIO_ESCOLHIDO", horarioSelecionado);

                startActivity(intent);
                finish();
            }
        });

    }
}
