package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityEvolucao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolucao);

        TextView btnVoltar = findViewById(R.id.btn_voltar_evolucao);
        MaterialButton btnCompartilhar = findViewById(R.id.btn_compartilhar_evolucao);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Compartilhar resultados (IA) //
        btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensagem = "Confira minha evolução na Clínica Maya RPG!\n" +
                        "📉 Nível de Dor: de 8.5 para 2.0\n" +
                        "📈 Alívio Lombar: 95%\n" +
                        "Estou muito feliz com os resultados!";

                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);
                intentCompartilhar.setType("text/plain");
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);

                startActivity(Intent.createChooser(intentCompartilhar, "Compartilhar evolução via:"));
            }
        });
    }
}