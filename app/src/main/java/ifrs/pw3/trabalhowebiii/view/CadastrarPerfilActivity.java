package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.dao.PessoaRepository;

public class CadastrarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_perfil);

        Button botao = findViewById(R.id.botaoSalvarPerfil);



//        botao apenas para teste , será deletado depois
        Button botaoVerUsers = findViewById(R.id.botaoVerUsuarios);
        botaoVerUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(getApplicationContext(), VerPerfilActivity.class);
                startActivity(intencao);
            }
        });


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PessoaRepository pessoaRepository = new PessoaRepository(getBaseContext());
                EditText nome = findViewById(R.id.edicaoNome);
                EditText senha = findViewById((R.id.edicaoSenha));
                if (nome.length() > 0 && senha.length() > 0){
                    String resultado = pessoaRepository.insert(nome.getText().toString(), senha.getText().toString());
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    limparCampos();
                }else
                    Toast.makeText(getApplicationContext(), "campos obrigatorios", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void limparCampos(){
        EditText nome = findViewById(R.id.edicaoNome);
        EditText senha = findViewById((R.id.edicaoSenha));
        nome.setText("");
        senha.setText("");
    }


}
