package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import ifrs.pw3.trabalhowebiii.R;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEmail = findViewById(R.id.edicaoNome);
        edtSenha = findViewById(R.id.edicaoSenha);
        btnLogin = findViewById(R.id.btnLoginEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);


        Button botaoEntrar = findViewById(R.id.btnLoginEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText nomeUser = findViewById(R.id.nomeLogin);
//                EditText senhaUser = findViewById(R.id.senhaLogin);


                Intent intent = new Intent(getApplicationContext(), MenuInicialActivity.class);
                startActivity(intent);
            }
        });


    }


    private void limparCampos() {
        EditText nomeUser = findViewById(R.id.nomeLogin);
        EditText senhaUser = findViewById(R.id.senhaLogin);
        nomeUser.setText("");
        senhaUser.setText("");
    }
}
