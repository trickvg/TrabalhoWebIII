package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import ifrs.pw3.trabalhowebiii.R;



public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText login;
    private EditText senha;
    private Button btnLogin;
//    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.nomeLogin);
        senha = findViewById(R.id.senhaLogin);
        btnLogin = findViewById(R.id.btnLoginEntrar);

        if(usuarioLogado()){
            openMainWindow();
        }
        else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!login.getText().toString().equals("") && !senha.getText().toString().equals("")) {
                        validateLogin(login.getText().toString(), senha.getText().toString());
                    } else
                        Toast.makeText(LoginActivity.this, "Informe email e senha!", Toast.LENGTH_SHORT).show();
                }
            });
//            btnCadastrar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(LoginActivity.this, CadastrarUsuarioActivity.class);
//                    startActivity(intent);
//                }
//            });

            mAuth = FirebaseAuth.getInstance();
        }
    }
    private Boolean usuarioLogado(){
        //Se o usuário já está logado não precisa fazer login novamente
        Log.d("Login", "instance"+FirebaseAuth.getInstance());
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null) return false;
        return true;
    }
    private void validateLogin(String email, String senha){

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    openMainWindow();
                    Toast.makeText(LoginActivity.this, "sucesso!", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(LoginActivity.this, "Dados de login inválidos!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openMainWindow(){
        Intent intent = new Intent(LoginActivity.this, PaginaInicialActivity.class);
        startActivity(intent);
    }
}
