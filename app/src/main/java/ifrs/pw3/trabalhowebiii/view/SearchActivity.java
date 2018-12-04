package ifrs.pw3.trabalhowebiii.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.adapter.MyAdapterCard;
import ifrs.pw3.trabalhowebiii.dao.ConfiguraFirebase;
import ifrs.pw3.trabalhowebiii.model.Evento;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ClickRecycler {
    private RecyclerView recyclerView;
    MyAdapterCard adapter;
    //    private List<User> listausers = new ArrayList<>();
    private ArrayList<Evento> listaEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evento_recycler);


        recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        for (int i = 0; i < 5; i++) {
//            listausers.add(User.carrega());
//            listausers.add(ListaRecyclerCard.carrega());


        //pega o valor que veio pela intenção
        Intent intent = getIntent();
        String parametroPesquisa = intent.getStringExtra("pesquisa");

        //ordenar os resultados pelo nome e mostrar somente os registros que possuem o nome passado como parâmetro na janela de pesquisa
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("eventos");
        Query pesquisa = reference.orderByChild("titulo_evento").equalTo(parametroPesquisa);

        pesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de eventos
                    Evento evento = ds.getValue(Evento.class);
                    evento.setId_evento(ds.getKey());
                    listaEventos.add(evento);
                }
                recyclerView.setAdapter(new MyAdapterCard(SearchActivity.this, listaEventos));
            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //        adapter = new MyAdapterCard(this, listaEventos, this);
        adapter = new MyAdapterCard(SearchActivity.this, listaEventos);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCustomClick(Object object) {
        System.out.println("funciona");
        Evento e = (Evento) object;
        System.out.println("Evento...  " + e.toString());
    }

}

