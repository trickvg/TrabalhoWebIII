package ifrs.pw3.trabalhowebiii.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.dao.PessoaRepository;
import ifrs.pw3.trabalhowebiii.model.Pessoa;

public class EditarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        Button botao = findViewById(R.id.botaoSalvarPerfil);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PessoaRepository pessoaRepository= new PessoaRepository(getBaseContext());
                EditText nome = findViewById(R.id.edicaoNome);
                EditText senha = findViewById((R.id.edicaoSenha));
                String resultado = pessoaRepository.insert(nome.getText().toString(), senha.getText().toString());
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                limparCampos();
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
