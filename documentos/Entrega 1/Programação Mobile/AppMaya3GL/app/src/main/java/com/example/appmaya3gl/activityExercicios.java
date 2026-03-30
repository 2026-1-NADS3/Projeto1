package com.example.appmaya3gl;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast; // Importamos a ferramenta de mensagens
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton; // Importamos o botão

public class activityExercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        MaterialButton btnConcluir = findViewById(R.id.btn_concluir_exercicio);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mensagem de conclusão.
                Toast.makeText(activityExercicios.this,
                        "Parabéns! Exercício concluído com sucesso.",
                        Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}