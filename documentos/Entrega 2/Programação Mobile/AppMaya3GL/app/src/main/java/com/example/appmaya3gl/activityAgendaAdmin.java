package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activityAgendaAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agenda_admin);

        TextView btnVoltar = findViewById(R.id.btn_voltar_agenda_admin);
        LinearLayout btnContato1 = findViewById(R.id.btn_contato_paciente_1);
        LinearLayout btnContato2 = findViewById(R.id.btn_contato_paciente_2);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //  Simulação //
        btnContato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityAgendaAdmin.this, "Abrindo WhatsApp com lembrete para Maria...", Toast.LENGTH_SHORT).show();
            }
        });

        // Simulação //
        btnContato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityAgendaAdmin.this, "Abrindo WhatsApp com boas-vindas para Natan...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}