package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityConfiguracoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        TextView btnVoltar = findViewById(R.id.btn_voltar_config);
        MaterialButton btnSair = findViewById(R.id.btn_sair_conta);

        // Switch = Ativar e desativar //
        Switch switchNotificacoes = findViewById(R.id.switch_notificacoes);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Volta para o Dashboard
            }
        });


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityConfiguracoes.this, "Saindo da conta...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activityConfiguracoes.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpa as telas abertas
                startActivity(intent);
            }
        });

        // Mensagens de notificação (imagináveis) //
        switchNotificacoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // <- ESPAÇO ADICIONADO AQUI
                if (isChecked) {
                    Toast.makeText(activityConfiguracoes.this, "Notificações Ativadas", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activityConfiguracoes.this, "Notificações Desativadas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}