package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import  ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.adapter.LinhaConsultaAdapter;
import ifrs.pw3.trabalhowebiii.dao.ConfiguraFirebase;
import ifrs.pw3.trabalhowebiii.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Evento> listEventos = new ArrayList<>();
    ListView lista = null;
    ArrayAdapter<Evento> listAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listEventos = new ArrayList<>();
        lista = findViewById(R.id.listaEventosPesquisa);
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEventos);

        //pega o valor que veio pela intenção
        Intent intent = getIntent();
        String parametroPesquisa = intent.getStringExtra("pesquisa");
//        Log.d("MSG", "s = " + parametroPesquisa);

        DatabaseReference reference = ConfiguraFirebase.getNo("eventos");
        listEventos = new ArrayList<>();
        //ordenar os resultados pelo nome e mostrar somente os registros que possuem o nome passado como parâmetro na janela de pesquisa
        Query pesquisa = reference.orderByChild("titulo_evento").equalTo(parametroPesquisa);

        pesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("MSG", "Dados do evento => " + dataSnapshot.getValue().toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Evento evento = ds.getValue(Evento.class);
                    evento.setId_evento(ds.getKey());
                    listEventos.add(evento);
                }
                lista.setAdapter(new LinhaConsultaAdapter(SearchActivity.this, listEventos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
