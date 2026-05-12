package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class activityExerciciosAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercicios_admin);

        TextView btnVoltar = findViewById(R.id.btn_voltar_cadastro_exercicios);
        EditText etNome = findViewById(R.id.et_nome_exercicio);
        EditText etDescricao = findViewById(R.id.et_descricao_exercicio);
        MaterialCardView btnAdicionarImagem = findViewById(R.id.card_adicionar_imagem);
        MaterialButton btnSalvar = findViewById(R.id.btn_salvar_exercicio);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdicionarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityExerciciosAdmin.this, "Abrindo galeria de fotos...", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString().trim();
                String descricao = etDescricao.getText().toString().trim();

                if (nome.isEmpty() || descricao.isEmpty()) {
                    Toast.makeText(activityExerciciosAdmin.this, "Preencha o nome e a descrição do exercício.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(activityExerciciosAdmin.this, "Exercício '" + nome + "' cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}