package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ifrs.pw3.trabalhowebiii.R;

public class CadastrarUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfSenha;
    private Button btnCancelar;
    private Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfSenha = findViewById(R.id.edtConfSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("") && !edtConfSenha.getText().toString().equals("")){
                    if( edtSenha.getText().toString().equals(edtSenha.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).
                                addOnCompleteListener(CadastrarUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(CadastrarUsuarioActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(CadastrarUsuarioActivity.this, "sucesso!", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                            Toast.makeText(CadastrarUsuarioActivity.this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(CadastrarUsuarioActivity.this, "Senha e confirmação de senha devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(CadastrarUsuarioActivity.this, "Informe os dados para o cadastro!", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(CadastrarUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();

    }
}
