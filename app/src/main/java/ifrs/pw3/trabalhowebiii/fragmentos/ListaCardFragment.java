package ifrs.pw3.trabalhowebiii.fragmentos;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.adapter.MyAdapterCard;
import ifrs.pw3.trabalhowebiii.model.Evento;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCardFragment extends Fragment {

    private RecyclerView recyclerView;
    MyAdapterCard adapter;
    private ArrayList<Evento> listaEventos = new ArrayList<>();


    public ListaCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_lista_card, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_list_card);
        StaggeredGridLayoutManager layoutManager =  new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
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
//                    Log.d("Evento:", "evento"+evento.toString());
                }
                recyclerView.setAdapter(new MyAdapterCard(ListaCardFragment.this, listaEventos));

            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        adapter = new MyAdapterCard(ListaCardFragment.this, listaEventos);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

}
