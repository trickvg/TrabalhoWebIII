package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ifrs.pw3.trabalhowebiii.R;

public class MenuInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        Button btnCadastrarUser = findViewById(R.id.btnCadastrarUsuario);

        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(getApplicationContext(), CadastrarPerfilActivity.class);
                startActivity(intencao);
            }
        });
    }
}
