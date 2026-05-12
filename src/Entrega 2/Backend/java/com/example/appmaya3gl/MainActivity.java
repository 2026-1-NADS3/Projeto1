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

        Animation slideUp = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        slideUp.setDuration(1200);

        cardAgendar.startAnimation(slideUp);
        cardAreaPaciente.startAnimation(slideUp);

        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgendamento = new Intent(MainActivity.this, AgendamentoActivity.class);
                startActivity(intentAgendamento);
            }
        });
        
        cardAreaPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intentDashboard);
            }
        });
    }
}