package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componentes visuais //
        MaterialCardView cardAgendar = findViewById(R.id.card_agendar_inicial);
        MaterialCardView cardAreaPaciente = findViewById(R.id.card_area_paciente);
        MaterialButton btnSejaPaciente = findViewById(R.id.btn_seja_paciente);

        // Animação //
        Animation slideUp = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        slideUp.setDuration(1200);

        // Aplica a animação nos três elementos da tela //
        cardAgendar.startAnimation(slideUp);
        cardAreaPaciente.startAnimation(slideUp);
        btnSejaPaciente.startAnimation(slideUp); // Botão animado também!

        // Para conhecimento: - Programação dos cliques, abaixo //

        // Clique para ir para a tela de Agendamento
        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgendamento = new Intent(MainActivity.this, AgendamentoActivity.class);
                startActivity(intentAgendamento);
            }
        });

        // Clique para ir para a Área do Paciente //
        cardAreaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intentDashboard);
            }
        });

        // Clique do botão novo: Seja Paciente //
        btnSejaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Como não temos a tela de cadastro, mostramos uma mensagem de acolhimento (DEVE CONTER CONTINUAÇÃO, NÃO ESQUECER) //
                Toast.makeText(MainActivity.this, "Iniciando cadastro de novo paciente...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}