package com.example.appmaya3gl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
// Importações do Firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activityCadastro extends AppCompatActivity {

    // Ferramentas do Firebase //
    private FirebaseAuth autenticacao;
    private FirebaseFirestore bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Firebase (DB Working) //
        autenticacao = FirebaseAuth.getInstance();
        bancoDeDados = FirebaseFirestore.getInstance();

        EditText etUsuario = findViewById(R.id.et_usuario_cadastro);
        EditText etEmail = findViewById(R.id.et_email_cadastro);
        EditText etSenha = findViewById(R.id.et_senha_cadastro);
        EditText etConfirmaSenha = findViewById(R.id.et_confirmar_senha_cadastro);

        MaterialButton btnFinalizarCadastro = findViewById(R.id.btn_confirmar_cadastro);
        TextView btnVoltar = findViewById(R.id.btn_voltarCadastro);

        btnVoltar.setOnClickListener(v -> finish());

        // Cadastrar //
        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pegando os textos que o paciente digitou (GET) //
                String usuario = etUsuario.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();
                String confirmaSenha = etConfirmaSenha.getText().toString().trim();

                // Verificar se tem campo vazio //
                if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
                    Toast.makeText(activityCadastro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // O Firebase exige senhas com pelo menos 6 caracteres //
                if (senha.length() < 6) {
                    Toast.makeText(activityCadastro.this, "A senha deve ter pelo menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar se as senhas são iguais //
                if (!senha.equals(confirmaSenha)) {
                    Toast.makeText(activityCadastro.this, "As senhas não coincidem. Digite novamente.", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(activityCadastro.this, "Criando conta...", Toast.LENGTH_SHORT).show();


                // Jogar para o DB (Firebase) //
                autenticacao.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sucesso! //
                                String idUsuario = autenticacao.getCurrentUser().getUid();

                                // Captar e incrementar os dados do paciente (PUT) //
                                Map<String, Object> paciente = new HashMap<>();
                                paciente.put("usuario", usuario);
                                paciente.put("email", email);
                                paciente.put("tipo_perfil", "paciente"); // Diferencia o paciente do Admin


                                bancoDeDados.collection("Usuarios").document(idUsuario)
                                        .set(paciente)
                                        .addOnSuccessListener(aVoid -> {
                                            // DEU TUDO CERTO! //
                                            Toast.makeText(activityCadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

                                            // Envia o paciente para o Dashboard e limpa as telas anteriores //
                                            Intent intent = new Intent(activityCadastro.this, DashboardActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(activityCadastro.this, "Erro ao salvar os dados: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                        });

                            } else {
                                // Else - Erro no cadastro, seja ele e-mail já em uso //
                                Toast.makeText(activityCadastro.this, "E-mail já em uso " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}