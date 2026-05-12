package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityComunicadosAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comunicados_admin);

        TextView btnVoltar = findViewById(R.id.btn_voltar_comunicados);
        EditText etAviso = findViewById(R.id.et_novo_aviso);
        MaterialButton btnPublicar = findViewById(R.id.btn_enviar_aviso_geral);
        MaterialButton btnReavaliacao = findViewById(R.id.btn_disparar_reavaliacao);
        MaterialButton btnAniversarios = findViewById(R.id.btn_disparar_aniversarios);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Publicar aviso Geral //
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aviso = etAviso.getText().toString().trim();

                if (aviso.isEmpty()) {
                    Toast.makeText(activityComunicadosAdmin.this, "Digite o texto do aviso antes de publicar.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(activityComunicadosAdmin.this, "Aviso publicado no mural dos pacientes!", Toast.LENGTH_LONG).show();
                etAviso.setText("");
            }
        });

        //  Disparos inatividade (Imaginário) //
        btnReavaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityComunicadosAdmin.this, "Enviado para 8 pacientes inativos!", Toast.LENGTH_SHORT).show();
            }
        });

        //  Disparo Aniversários (Imaginário) //
        btnAniversarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityComunicadosAdmin.this, "Felicitações para os aniversariantes do mês!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}