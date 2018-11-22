package ifrs.pw3.trabalhowebiii.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.adapter.MyAdapterCard;
import ifrs.pw3.trabalhowebiii.model.Evento;

public class ListarEventoCardActivity extends AppCompatActivity implements ClickRecycler {
    private RecyclerView recyclerView;
    MyAdapterCard adapter;
    private ArrayList<Evento> listaEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evento_card);

        recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("eventos");
        reference.addValueEventListener(new ValueEventListener() {
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
//                    lista.setAdapter(new LinhaConsultaAdapter(ListarEventoActivity.this, listUsuarios));
            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        adapter = new MyAdapterCard(this, listaEventos, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCustomClick(Object object) {
        System.out.println("funciona...");
        Evento e = (Evento) object;
        System.out.println("Evento = " + e.toString());
    }

}

