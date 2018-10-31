//package ifrs.pw3.trabalhowebiii.view;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import ifrs.pw3.trabalhowebiii.R;
//import ifrs.pw3.trabalhowebiii.dao.NotaRepository;
//
//public class CadastrarNotaActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cadastrar_nota);
//
//
//        Button cadastrarNotas = findViewById(R.id.btnSalvarNota);
//
//        cadastrarNotas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NotaRepository notaRepository = new NotaRepository(getBaseContext());
//                EditText titulo = findViewById(R.id.editTextTituloNota);
//                EditText descricao = findViewById(R.id.editTextdescricaoNota);
//                if (titulo.length() > 0 && descricao.length() > 0) {
//                    String resultado = notaRepository.insert(titulo.getText().toString(), descricao.getText().toString());
//                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
//                    limparCamposNota();
//                } else
//                    Toast.makeText(getApplicationContext(), "campos obrigatorios", Toast.LENGTH_LONG).show();
//
////                Toast.makeText(getApplicationContext(), "opa", Toast.LENGTH_LONG);
//
//            }
//        });
//    }
//
//    private void limparCamposNota() {
//        EditText titulo = findViewById(R.id.editTextTituloNota);
//        EditText descricao = findViewById((R.id.editTextdescricaoNota));
//        titulo.setText("");
//        descricao.setText("");
//    }
//
//}
//
