package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

// Importações do Firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class activityExercicios3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios3);

        TextView btnVoltar = findViewById(R.id.btn_voltar_ex3);
        MaterialButton btnConcluir = findViewById(R.id.btn_concluir_exercicio3);

        // Volta para a Tela 2 //
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Finaliza o treino e salva no Firebase //
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Aviso visual para o paciente não clicar duas vezes //
                Toast.makeText(activityExercicios3.this, "Salvando seu progresso na nuvem...", Toast.LENGTH_SHORT).show();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                if (auth.getCurrentUser() != null) {
                    String idPaciente = auth.getCurrentUser().getUid();
                    DocumentReference documentoPaciente = db.collection("Usuarios").document(idPaciente);

                    // Verificando progresso do paciente no Firebase //
                    documentoPaciente.get().addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {

                            Long progressoAtualNuvem = documentSnapshot.getLong("progresso_diario");
                            // Se for nulo (paciente novo que nunca treinou), começa com 5% //
                            int progressoAtual = (progressoAtualNuvem != null) ? progressoAtualNuvem.intValue() : 5;

                            //  Adicionando os +5% do treino de hoje! //
                            int novoProgresso = progressoAtual + 5;

                            //  Trava para não passar de 100% //
                            if (novoProgresso > 100) {
                                novoProgresso = 100;
                            }

                            // Salvando o novo número de volta no Firebase //
                            documentoPaciente.update("progresso_diario", novoProgresso)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(activityExercicios3.this, "Parabéns! Treino finalizado.", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(activityExercicios3.this, DashboardActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(activityExercicios3.this, "Erro ao salvar progresso: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    });
                }
            }
        });
    }
}