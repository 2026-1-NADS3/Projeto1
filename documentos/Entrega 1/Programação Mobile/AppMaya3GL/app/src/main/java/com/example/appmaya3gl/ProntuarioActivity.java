package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ProntuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prontuario);

        MaterialButton btnDownload1 = findViewById(R.id.btn_download_relatorio_1);
        btnDownload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProntuarioActivity.this, "Baixando Relatório PDF...", Toast.LENGTH_SHORT).show();
            }
        });

        MaterialButton btnDownload2 = findViewById(R.id.btn_download_relatorio_2);
        btnDownload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProntuarioActivity.this, "Iniciando download do PDF...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}