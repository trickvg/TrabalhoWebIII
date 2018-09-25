package ifrs.pw3.trabalhowebiii.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ifrs.pw3.trabalhowebiii.R;

public class CadastrarNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_nota);


        Button cadastrarNotas = findViewById(R.id.btnSalvarNota);

        cadastrarNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText titulo = findViewById(R.id.editTextTituloNota);
//                EditText descricao = findViewById(R.id.editTextdescricaoNota);
                Toast.makeText(getApplicationContext(), "opa", Toast.LENGTH_LONG);

            }
        });



    }
}
