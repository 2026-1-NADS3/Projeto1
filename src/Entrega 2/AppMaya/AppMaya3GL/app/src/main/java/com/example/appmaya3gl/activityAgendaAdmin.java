package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class activityAgendaAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_admin);

        TextView btnVoltar = findViewById(R.id.btn_voltar_agenda_admin);

        // Pegamos a "caixa vazia" - ACTIVITY (item_card_agenda) //
        LinearLayout containerAgendamentos = findViewById(R.id.container_lista_agendamentos);

        btnVoltar.setOnClickListener(v -> finish());

        FirebaseFirestore bancoDeDados = FirebaseFirestore.getInstance();

        // Avisa a Dra. Maya que está carregando...
        Toast.makeText(this, "Buscando agendamentos...", Toast.LENGTH_SHORT).show();

        // "Agendamentos" e pega TUDO que tem lá //
        bancoDeDados.collection("Agendamentos")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    // Remover tudo //
                    containerAgendamentos.removeAllViews();

                    // Se não tiver nenhum paciente agendado //
                    if (queryDocumentSnapshots.isEmpty()) {
                        Toast.makeText(activityAgendaAdmin.this, "Nenhum agendamento para hoje.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Faz um "loop" (gira) para cada paciente encontrado no Firebase //
                    for (QueryDocumentSnapshot documento : queryDocumentSnapshots) {

                        //  Pega os dados desse paciente (GET) //
                        String nomePaciente = documento.getString("nome_paciente");
                        String dataConsulta = documento.getString("data_consulta");
                        String horaConsulta = documento.getString("hora_consulta");
                        String focoConsulta = documento.getString("foco");
                        String statusConsulta = documento.getString("status");

                        View cartaoPaciente = LayoutInflater.from(activityAgendaAdmin.this)
                                .inflate(R.layout.item_card_agenda, containerAgendamentos, false);

                        // Pega os textos de dentro DESSE cartão novo //
                        TextView tvNome = cartaoPaciente.findViewById(R.id.tv_item_nome);
                        TextView tvHora = cartaoPaciente.findViewById(R.id.tv_item_hora);
                        TextView tvData = cartaoPaciente.findViewById(R.id.tv_item_data);
                        TextView tvFoco = cartaoPaciente.findViewById(R.id.tv_item_foco);
                        TextView tvStatus = cartaoPaciente.findViewById(R.id.tv_item_status);

                        // Cola os dados do Firebase no cartão //
                        tvNome.setText("Paciente " + nomePaciente);
                        tvHora.setText("Agendamento para " + horaConsulta + "h");
                        tvData.setText(dataConsulta);
                        tvFoco.setText("Foco: " + focoConsulta);
                        tvStatus.setText(statusConsulta);

                        containerAgendamentos.addView(cartaoPaciente);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(activityAgendaAdmin.this, "Erro ao carregar a agenda: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}