package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityConfirmacaoAgendamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_agendamento);
        MaterialButton btnAbrirMapa = findViewById(R.id.btn_abrir_mapa);
        TextView btnVoltar = findViewById(R.id.btn_voltar_confirmacao);
        TextView tvHorario = findViewById(R.id.tv_horario_selecionado);
        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_agendamento_final);

        Intent intentQueTrouxeDados = getIntent();

        if (intentQueTrouxeDados.hasExtra("HORARIO_ESCOLHIDO")) {
            String horario = intentQueTrouxeDados.getStringExtra("HORARIO_ESCOLHIDO");
            // Trocamos o texto da tela pelo horário real //
            tvHorario.setText(horario);
        } else {
            tvHorario.setText("Horário não selecionado");
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityConfirmacaoAgendamento.this, AgendamentoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                finish(); // Volta para a escolha de horários //
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityConfirmacaoAgendamento.this, "Agendamento confirmado com sucesso!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(activityConfirmacaoAgendamento.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        btnAbrirMapa.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                // Endereço completo da Dra. Maya //
                String enderecoMaya = "R. Rio Grande, 141 - Vila Mariana, São Paulo - SP";

                // Cria a URI especial do Maps (IA) //
                String uriMapa = "geo:0,0?q=" + android.net.Uri.encode(enderecoMaya);

                android.content.Intent intentMapa = new android.content.Intent(android.content.Intent.ACTION_VIEW);
                intentMapa.setData(android.net.Uri.parse(uriMapa));

                // Se não abrir o maps, acolher o paciente //
                try {
                    startActivity(intentMapa);
                } catch (Exception e) {
                    android.widget.Toast.makeText(activityConfirmacaoAgendamento.this, "Erro ao abrir o mapa.", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}