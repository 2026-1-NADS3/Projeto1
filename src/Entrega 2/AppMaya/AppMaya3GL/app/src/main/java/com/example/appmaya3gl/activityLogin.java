package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class activityLogin extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get Firebase - pegando info //
        autenticacao = FirebaseAuth.getInstance();

        MaterialButton btnConfirmar = findViewById(R.id.btn_confirmar_login);
        TextView tvIrParaCadastro = findViewById(R.id.tv_ir_para_cadastro);
        TextView tvEsqueceuSenha = findViewById(R.id.tv_esqueceu_senha);

        EditText etEmail = findViewById(R.id.et_usuario_login);
        EditText etSenha = findViewById(R.id.et_senha_login);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailDigitado = etEmail.getText().toString().trim();
                String senhaDigitada = etSenha.getText().toString().trim();

                // Impedir envio de campos vazios //
                if (emailDigitado.isEmpty() || senhaDigitada.isEmpty()) {
                    Toast.makeText(activityLogin.this, "Por favor, preencha o e-mail e a senha.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Dra. Maya - Tela para o admin //
                if (emailDigitado.equals("maya_admin") && senhaDigitada.equals("maya123")) {
                    Toast.makeText(activityLogin.this, "Bem-vinda, Dra. Maya!", Toast.LENGTH_SHORT).show();
                    Intent intentAdmin = new Intent(activityLogin.this, activityAdmin.class);
                    startActivity(intentAdmin);
                    finish();
                    return;
                }


                // Login do paciente (Firebase) //
                Toast.makeText(activityLogin.this, "Autenticando...", Toast.LENGTH_SHORT).show();

                autenticacao.signInWithEmailAndPassword(emailDigitado, senhaDigitada)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sucesso! //
                                Toast.makeText(activityLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                                // Direcionando para o Dashboard (Intent) //
                                Intent intentPaciente = new Intent(activityLogin.this, MainActivity.class);
                                startActivity(intentPaciente);
                                finish();
                            } else {
                                // Falha! //
                                Toast.makeText(activityLogin.this, "Erro: E-mail ou senha incorretos.", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        tvIrParaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityLogin.this, activityCadastro.class);
                startActivity(intent);
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