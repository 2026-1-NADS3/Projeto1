package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_cadastro);
        TextView btnVoltarCadastro = findViewById(R.id.btn_voltarCadastro);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityCadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activityCadastro.this, MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}