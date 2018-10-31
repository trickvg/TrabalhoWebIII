//package ifrs.pw3.trabalhowebiii.view;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import ifrs.pw3.trabalhowebiii.R;
//
//public class MenuInicialActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_inicial);
//
//        Button btnCadastrarUser = findViewById(R.id.btnCadastrarUsuario);
//        Button btnCadastrarNota = findViewById(R.id.btnCadastrarNota);
//        Button btnLocalizacaoIFRS = findViewById(R.id.btnLocalizacaoIFRS);
//        Button btnEditarNota = findViewById(R.id.btnEditarNota);
//
//        btnEditarNota.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intencao = new Intent(getApplicationContext(), ListNotasActivity.class);
//                startActivity(intencao);
//
//            }
//        });
//
//        btnLocalizacaoIFRS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intencao = new Intent(Intent.ACTION_VIEW);
//                intencao.setData(Uri.parse("geo:0,0?q=-30.026221, -51.221203(IFRS POA)?z=15"));
//                if (intencao.resolveActivity(getPackageManager()) != null){
//                    startActivity(intencao);
//                }
//            }
//        });
//
//        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intencao = new Intent(getApplicationContext(), CadastrarPerfilActivity.class);
//                startActivity(intencao);
//            }
//        });
//
//
//        btnCadastrarNota.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intencao = new Intent(getApplicationContext(), CadastrarNotaActivity.class);
//                startActivity(intencao);
//            }
//        });
//    }
//}
