package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class activityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_login);
        TextView tvIrParaCadastro = findViewById(R.id.tv_ir_para_cadastro);
        TextView tvEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha);

        // Campo de dígito -- usuario e senha //
        EditText etUsuario = findViewById(R.id.et_usuario_login);
        EditText etSenha = findViewById(R.id.et_senha_login);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioDigitado = etUsuario.getText().toString();
                String senhaDigitada = etSenha.getText().toString();

                // If e else para login com o administrador (maya) //
                if (usuarioDigitado.equals("maya_admin") && senhaDigitada.equals("maya123")) {

                    Toast.makeText(activityLogin.this, "Bem-vinda, Dra. Maya!", Toast.LENGTH_SHORT).show();

                    Intent intentAdmin = new Intent(activityLogin.this, activityAdmin.class);
                    startActivity(intentAdmin);
                    finish(); // Esta intent é para o admin //
                } else {
                    Toast.makeText(activityLogin.this, "Login de paciente realizado!", Toast.LENGTH_SHORT).show();

                    Intent intentPaciente = new Intent(activityLogin.this, MainActivity.class);
                    startActivity(intentPaciente);
                    finish(); // Esta intent é para o user //
                }
            }
        });

        tvIrParaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activityCadastro.class);
                startActivity(intent); // Essa intent é para o cadastro //
            }
        });

        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activityCadastro.class);
                startActivity(intent);  // Sem função para o esqueci a senha, portanto, ir para cadastro //
            }
        });
    }
}