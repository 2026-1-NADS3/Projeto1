package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        MaterialCardView cardExercicios = findViewById(R.id.card_dicas);
        MaterialCardView cardProntuario = findViewById(R.id.card_prontuario);
        MaterialCardView cardAgendar = findViewById(R.id.card_agendar);
        android.widget.ImageView fotoPerfil = findViewById(R.id.iv_profile_pic);
        android.widget.TextView btnVoltar = findViewById(R.id.btn_voltar4);
        android.widget.ImageView btnConfiguracoes = findViewById(R.id.iv_settings);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        com.google.android.material.button.MaterialButton btnWhatsapp = findViewById(R.id.btn_whatsapp_contato);

        // Cards //
        cardExercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExercicioCard = new Intent(DashboardActivity.this, activityExercicios.class);
                startActivity(intentExercicioCard);
            }
        });

        cardProntuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProntuarioCard = new Intent(DashboardActivity.this, ProntuarioActivity.class);
                startActivity(intentProntuarioCard);
            }
        });

        cardAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgendarCard = new Intent(DashboardActivity.this, AgendamentoActivity.class);
                startActivity(intentAgendarCard);
            }
        });

        // Voltar e ida ao perfil //
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a tela
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerfil = new Intent(DashboardActivity.this, PerfilActivity.class);
                startActivity(intentPerfil);
            }
        });

        // NAV //
        bottomNav.setOnItemSelectedListener(item -> {

            int idClicado = item.getItemId();

            if (idClicado == R.id.nav_exercicios) {
                Intent intentNavExercicios = new Intent(DashboardActivity.this, activityExercicios.class);
                startActivity(intentNavExercicios);
                return true;
            }

            else if (idClicado == R.id.nav_historico) {
                Intent intentNavProntuario = new Intent(DashboardActivity.this, ProntuarioActivity.class);
                startActivity(intentNavProntuario);
                return true;
            }

            else if (idClicado == R.id.nav_perfil) {
                Intent intentPerfil = new Intent(DashboardActivity.this, PerfilActivity.class);
                startActivity(intentPerfil);
                return true;
            }

            return false;
        });

        // Configs //
        btnConfiguracoes.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(DashboardActivity.this, activityConfiguracoes.class);
                startActivity(intent);
            }
        });

        // Config mensagem direto para a Maya (IA) //
        btnWhatsapp.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                // Número da Maya //
                String numeroMaya = "55 11 998820868";

                // Mensagem automática //
                String mensagemAutomatica = "Olá, Dra. Maya! Gostaria de tirar uma dúvida com você!";

                // Link ZAP //
                String urlWhatsapp = "https://wa.me/" + numeroMaya + "?text=" + android.net.Uri.encode(mensagemAutomatica);

                android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(android.net.Uri.parse(urlWhatsapp));

                // O bloco try/catch é uma rede de segurança, mesma lógica do full stack. //
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    android.widget.Toast.makeText(DashboardActivity.this, "Erro ao abrir o WhatsApp. Verifique se ele está instalado.", android.widget.Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Tentando aplicar o progresso do treino finalizado para a barrinha "Gameficação" //
    @Override
    protected void onResume() {
        super.onResume();

        android.widget.ProgressBar progressBar = findViewById(R.id.progress_bar);
        android.widget.TextView tvProgressPercent = findViewById(R.id.tv_progresso_valor);

        android.content.SharedPreferences prefs = getSharedPreferences("MayaPrefs", MODE_PRIVATE);

        int progressoSalvo = prefs.getInt("total_progress", 33);

        // Define o preenchimento da barrinha //
        progressBar.setProgress(progressoSalvo);

        // Define o texto do percentual //
        tvProgressPercent.setText(progressoSalvo + "%");
    }


}