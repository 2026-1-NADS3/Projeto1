package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import android.widget.CalendarView; // Importe o CalendarView

public class AgendamentoActivity extends AppCompatActivity {

    private String horarioSelecionado = "";
    private String dataSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_agendamento);
        android.widget.TextView btnVoltar = findViewById(R.id.btn_voltar);

        CalendarView calendarioNaTela = findViewById(R.id.calendario_maya);

        if (calendarioNaTela != null) {
            // Define a data mínima como o exato momento de hoje em milissegundos //
            calendarioNaTela.setMinDate(System.currentTimeMillis() - 1000);

            // Ouvinte para quando o paciente tocar em algum dia //
            calendarioNaTela.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    // O mês começa em 0 no Java, então somamos 1 //
                    dataSelecionada = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
                }
            });
        }

        // Chips de horários disponíveis //
        Chip chip0800 = findViewById(R.id.chip_0800);
        Chip chip0900 = findViewById(R.id.chip_0900);
        Chip chip1030 = findViewById(R.id.chip_1030);
        Chip chip1400 = findViewById(R.id.chip_1400);
        Chip chip1530 = findViewById(R.id.chip_1530);
        Chip chip1700 = findViewById(R.id.chip_1700);

        if (chip0800 != null) chip0800.setOnClickListener(v -> horarioSelecionado = "08:00");
        if (chip0900 != null) chip0900.setOnClickListener(v -> horarioSelecionado = "09:00");
        if (chip1030 != null) chip1030.setOnClickListener(v -> horarioSelecionado = "10:30");
        if (chip1400 != null) chip1400.setOnClickListener(v -> horarioSelecionado = "14:00");
        if (chip1530 != null) chip1530.setOnClickListener(v -> horarioSelecionado = "15:30");
        if (chip1700 != null) chip1700.setOnClickListener(v -> horarioSelecionado = "17:00");

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(AgendamentoActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });


        // Confirmando para o Firebase //
        if (btnConfirmar != null) {
            btnConfirmar.setOnClickListener(v -> {

                if (dataSelecionada.isEmpty()) {
                    Toast.makeText(AgendamentoActivity.this, "Por favor, toque no dia desejado no calendário.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (horarioSelecionado.isEmpty()) {
                    Toast.makeText(AgendamentoActivity.this, "Por favor, selecione um horário.", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                if (auth.getCurrentUser() != null) {
                    String idPaciente = auth.getCurrentUser().getUid();

                    db.collection("Usuarios").document(idPaciente).get()
                            .addOnSuccessListener(documentSnapshot -> {
                                if (documentSnapshot.exists()) {
                                    String nomeDoPaciente = documentSnapshot.getString("usuario");

                                    Map<String, Object> agendamento = new HashMap<>();
                                    agendamento.put("nome_paciente", nomeDoPaciente);
                                    agendamento.put("id_paciente", idPaciente);
                                    agendamento.put("data_consulta", "Dia " + dataSelecionada);
                                    agendamento.put("hora_consulta", horarioSelecionado);
                                    agendamento.put("foco", "Avaliação Postural");
                                    agendamento.put("status", "CONFIRMADO");

                                    db.collection("Agendamentos")
                                            .add(agendamento)
                                            .addOnSuccessListener(documentReference -> {

                                                Intent intent = new Intent(AgendamentoActivity.this, activityConfirmacaoAgendamento.class);
                                                intent.putExtra("HORARIO_ESCOLHIDO", horarioSelecionado);
                                                intent.putExtra("DATA_ESCOLHIDA", dataSelecionada);
                                                startActivity(intent);
                                                finish();
                                            })
                                            .addOnFailureListener(e -> {
                                                Toast.makeText(AgendamentoActivity.this, "Erro ao agendar: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                            });
                                }
                            });
                }
            });
        }
    }
}