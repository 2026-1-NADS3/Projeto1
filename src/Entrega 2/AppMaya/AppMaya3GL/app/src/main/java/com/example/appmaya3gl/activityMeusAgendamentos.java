package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class activityMeusAgendamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        TextView btnVoltar = findViewById(R.id.btn_voltar_meus_agendamentos);
        LinearLayout containerMeusAgendamentos = findViewById(R.id.container_lista_agendamentos);

        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(v -> finish());
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore bancoDeDados = FirebaseFirestore.getInstance();

        if (auth.getCurrentUser() != null) {
            String idUsuarioLogado = auth.getCurrentUser().getUid();

            Toast.makeText(this, "Buscando seus agendamentos...", Toast.LENGTH_SHORT).show();

            bancoDeDados.collection("Agendamentos")
                    .whereEqualTo("id_paciente", idUsuarioLogado)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        containerMeusAgendamentos.removeAllViews();

                        if (queryDocumentSnapshots.isEmpty()) {
                            Toast.makeText(activityMeusAgendamentos.this, "Você ainda não tem consultas agendadas.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        for (QueryDocumentSnapshot documento : queryDocumentSnapshots) {


                            // Pegar o ID deste agendamento para poder cancelar //
                            String idDoDocumento = documento.getId();

                            String nomePaciente = documento.getString("nome_paciente");
                            String dataConsulta = documento.getString("data_consulta");
                            String horaConsulta = documento.getString("hora_consulta");
                            String focoConsulta = documento.getString("foco");
                            String statusConsulta = documento.getString("status");

                            // Molde do cartão //
                            View cartaoPaciente = LayoutInflater.from(activityMeusAgendamentos.this)
                                    .inflate(R.layout.item_card_agenda, containerMeusAgendamentos, false);

                            TextView tvNome = cartaoPaciente.findViewById(R.id.tv_item_nome);
                            TextView tvHora = cartaoPaciente.findViewById(R.id.tv_item_hora);
                            TextView tvData = cartaoPaciente.findViewById(R.id.tv_item_data);
                            TextView tvFoco = cartaoPaciente.findViewById(R.id.tv_item_foco);
                            TextView tvStatus = cartaoPaciente.findViewById(R.id.tv_item_status);
                            TextView btnCancelar = cartaoPaciente.findViewById(R.id.btn_cancelar_agendamento);

                            tvNome.setText("Paciente " + nomePaciente);
                            tvHora.setText("Agendamento para " + horaConsulta + "h");
                            tvData.setText(dataConsulta);
                            tvFoco.setText("Foco: " + focoConsulta);
                            tvStatus.setText(statusConsulta);

                            // Botão de cancelar //
                            if (btnCancelar != null) {
                                btnCancelar.setOnClickListener(v -> {
                                    // Cria uma caixinha de alerta para evitar clique acidental do paciente //
                                    new android.app.AlertDialog.Builder(activityMeusAgendamentos.this)
                                            .setTitle("Cancelar Sessão")
                                            .setMessage("Tem certeza que deseja cancelar esta sessão?")
                                            .setPositiveButton("Sim, cancelar", (dialog, which) -> {

                                                // Deleta o documento do Firebase //
                                                bancoDeDados.collection("Agendamentos").document(idDoDocumento)
                                                        .delete()
                                                        .addOnSuccessListener(aVoid -> {
                                                            Toast.makeText(activityMeusAgendamentos.this, "Sessão cancelada com sucesso.", Toast.LENGTH_SHORT).show();
                                                            containerMeusAgendamentos.removeView(cartaoPaciente);
                                                        })
                                                        .addOnFailureListener(erro -> {
                                                            Toast.makeText(activityMeusAgendamentos.this, "Erro ao cancelar: " + erro.getMessage(), Toast.LENGTH_SHORT).show();
                                                        });
                                            })
                                            .setNegativeButton("Não", null)
                                            .show();
                                });
                            }

                            containerMeusAgendamentos.addView(cartaoPaciente);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(activityMeusAgendamentos.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }
    }
}