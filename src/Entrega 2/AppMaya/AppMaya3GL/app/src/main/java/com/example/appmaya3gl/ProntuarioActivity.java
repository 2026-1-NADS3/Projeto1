package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProntuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prontuario);

        TextView btnVoltar = findViewById(R.id.btn_voltar3);

        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // Get - Pegando os dados dos usuários cadastrados //
        FirebaseAuth autenticacao = FirebaseAuth.getInstance();
        FirebaseFirestore bancoDeDados = FirebaseFirestore.getInstance();


        com.google.android.material.button.MaterialButton btnBaixarAvaliacao = findViewById(R.id.btn_download_relatorio_1);
        com.google.android.material.button.MaterialButton btnBaixarEvolucao = findViewById(R.id.btn_download_relatorio_2);


        TextView tvAvaliacaoInicial = findViewById(R.id.tv_tipo_prontuario_1);
        TextView tvRelatorioEvolucao = findViewById(R.id.tv_tipo_prontuario_2);

        // Verifica se tem um paciente logado //
        if (autenticacao.getCurrentUser() != null) {
            String idUsuarioLogado = autenticacao.getCurrentUser().getUid();

            // Buscar os dados do paciente //
            bancoDeDados.collection("Usuarios").document(idUsuarioLogado)
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                // Trás o nome do DB (Firebase) //
                                String nomeDaNuvem = documentSnapshot.getString("usuario");

                                if (nomeDaNuvem != null) {
                                    // Junta a frase padrão + o nome do paciente //
                                    if (tvAvaliacaoInicial != null) {
                                        tvAvaliacaoInicial.setText("Avaliação Inicial " + nomeDaNuvem);
                                    }

                                    if (tvRelatorioEvolucao != null) {
                                        tvRelatorioEvolucao.setText("Relatório de Evolução " + nomeDaNuvem);
                                    }
                                }
                            }
                        }
                    });

            // Baixar relatório do paciente (Ilustrativo) //
            if (btnBaixarAvaliacao != null) {
                btnBaixarAvaliacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.widget.Toast.makeText(ProntuarioActivity.this, "Iniciando o download do PDF: Avaliação Inicial...", android.widget.Toast.LENGTH_LONG).show();
                    }
                });
            }

            if (btnBaixarEvolucao != null) {
                btnBaixarEvolucao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.widget.Toast.makeText(ProntuarioActivity.this, "Iniciando o download do PDF: Relatório de Evolução...", android.widget.Toast.LENGTH_LONG).show();
                    }
                });
            }

            if (btnBaixarAvaliacao != null) {
                btnBaixarAvaliacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Coloque aqui o link do Google Drive onde você salvou o PDF ilustrativo
                        String urlDoPdf = "https://drive.google.com/file/d/1oYTfSlhBHZ_V6qZTB7kHIIhT7qo5ZWjF/view?usp=sharing";

                        android.widget.Toast.makeText(ProntuarioActivity.this, "Abrindo Relatório Clínico...", android.widget.Toast.LENGTH_SHORT).show();

                        android.content.Intent intentHtml = new android.content.Intent(android.content.Intent.ACTION_VIEW);
                        intentHtml.setData(android.net.Uri.parse(urlDoPdf));

                        try {
                            startActivity(intentHtml);
                        } catch (Exception e) {
                            android.widget.Toast.makeText(ProntuarioActivity.this, "Erro ao abrir o arquivo. Verifique se possui um navegador instalado.", android.widget.Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        }
    }
}