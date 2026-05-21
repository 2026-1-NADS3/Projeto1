package com.example.appmaya3gl; // Mantenha a sua linha original aqui!

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        com.google.android.material.button.MaterialButton btnMeusAgendamentos = findViewById(R.id.btn_meus_agendamentos);
        TextView btnVoltar = findViewById(R.id.btn_voltar_perfil);
        TextView btnTrocarSenha = findViewById(R.id.btn_grafico_evolucao);
        MaterialButton btnHistorico = findViewById(R.id.btn_historico_perfil);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTrocarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, activityEvolucao.class);
                startActivity(intent);
            }
        });

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, ProntuarioActivity.class);
                startActivity(intent);
            }
        });

        // Firebase (BD trabalhando) //
        FirebaseAuth autenticacao = FirebaseAuth.getInstance();
        FirebaseFirestore bancoDeDados = FirebaseFirestore.getInstance();


        TextView tvNomePerfil = findViewById(R.id.tv_nome_paciente);

        if (autenticacao.getCurrentUser() != null) {
            String idUsuarioLogado = autenticacao.getCurrentUser().getUid();

            bancoDeDados.collection("Usuarios").document(idUsuarioLogado)
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                String nomeDaNuvem = documentSnapshot.getString("usuario");

                                if (nomeDaNuvem != null) {
                                    // Nome do perfil em maíusculo //
                                    tvNomePerfil.setText(nomeDaNuvem.toUpperCase());
                                }
                            }
                        }
                    });

            if (btnMeusAgendamentos != null) {
                btnMeusAgendamentos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PerfilActivity.this, activityMeusAgendamentos.class);
                        startActivity(intent);
                    }
                });
            }

        }
    }
}