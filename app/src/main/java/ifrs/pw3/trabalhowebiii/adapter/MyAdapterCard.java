package ifrs.pw3.trabalhowebiii.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.dao.ConfiguraFirebase;
import ifrs.pw3.trabalhowebiii.fragmentos.ListaCardFragment;
import ifrs.pw3.trabalhowebiii.model.Evento;
import ifrs.pw3.trabalhowebiii.view.AtualizarEventoActivity;
import ifrs.pw3.trabalhowebiii.view.ClickRecycler;
import ifrs.pw3.trabalhowebiii.view.ListarEventoRecycler;
import ifrs.pw3.trabalhowebiii.view.SearchActivity;

public class MyAdapterCard extends RecyclerView.Adapter<MyAdapterCard.MyViewHolderCard> {

    public static ClickRecycler clickRecycler;
    private List<Evento> listaEventos;

    ListarEventoRecycler listarEventoRecycler;
    SearchActivity searchActivity;
    ListaCardFragment lcf;

    //  INICIO CONSTRUTORES
    public MyAdapterCard(SearchActivity searchActivity, List<Evento> list) {
        this.searchActivity = searchActivity;
        this.listaEventos = list;
    }

    public MyAdapterCard(ListarEventoRecycler listarEventoRecycler, List<Evento> list) {
        this.listarEventoRecycler = listarEventoRecycler;
        this.listaEventos = list;
    }


    //    construtor de teste
    public MyAdapterCard(ListaCardFragment lcf, List<Evento> list) {
        this.lcf = lcf;
        this.listaEventos = list;
    }

    //  FIM CONSTRUTORES

    @Override
    public MyViewHolderCard onCreateViewHolder(ViewGroup viewGroup, int i) {
        System.out.println("Contexto=" + viewGroup.getContext());
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list, viewGroup, false);
        return new MyViewHolderCard(itemView);
    }


        @Override
    public void onBindViewHolder(MyViewHolderCard viewHolder, final int position) {
        Evento user = listaEventos.get(position);
        viewHolder.textViewTitulo.setText(user.getTitulo_evento());
        viewHolder.textViewDescricao.setText(user.getDescricao_evento());
        viewHolder.textViewLocal.setText(user.getLocal_evento());
        viewHolder.textViewHorario.setText(user.getHorario_evento());
        viewHolder.textViewData.setText(user.getData_evento());

            if (lcf != null) {
                viewHolder.buttonExcluir.setVisibility(View.INVISIBLE);
                viewHolder.buttonEditar.setVisibility(View.INVISIBLE);
            }

            viewHolder.buttonExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mensagem = "Registro excluído com sucesso!";
                    final Evento evento = listaEventos.get(position);
                    final DatabaseReference reference = ConfiguraFirebase.getNo("eventos");


                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!listaEventos.isEmpty()) {
                                listaEventos.remove(position);
                                reference.child(evento.getId_evento()).removeValue();
                                notifyList();

                            }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                    });

                }

            });

            viewHolder.buttonEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //EXERCÍCIO FAZER A TAREFA DE EDIÇÃO USANDO A LÓGICA DA EXCLUSÃO

                    final Evento evento = listaEventos.get(position);

                    if (listarEventoRecycler != null) {
                        Intent intent = new Intent(listarEventoRecycler, AtualizarEventoActivity.class);
                        intent.putExtra("id_evento", evento.getId_evento());
                        intent.putExtra("titulo_evento", evento.getTitulo_evento());
                        intent.putExtra("descricao_evento", evento.getDescricao_evento());
                        intent.putExtra("local_evento", evento.getLocal_evento());
                        intent.putExtra("horario_evento", evento.getHorario_evento());
                        intent.putExtra("data_evento", evento.getData_evento());
//                    Log.d("msg", "2");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        listarEventoRecycler.startActivity(intent);
                    }
                    if (searchActivity != null) {

                        Intent intent = new Intent(searchActivity, AtualizarEventoActivity.class);
                        intent.putExtra("id_evento", evento.getId_evento());
                        intent.putExtra("titulo_evento", evento.getTitulo_evento());
                        intent.putExtra("descricao_evento", evento.getDescricao_evento());
                        intent.putExtra("local_evento", evento.getLocal_evento());
                        intent.putExtra("horario_evento", evento.getHorario_evento());
                        intent.putExtra("data_evento", evento.getData_evento());

                        searchActivity.startActivity(intent);
                    }


                }
            });


    }

    private void removeItem(int position) {
        listaEventos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaEventos.size());
    }

    @Override
    public int getItemCount() {
        return listaEventos != null ? listaEventos.size() : 0;

    }

    private void notifyList() {
        this.notifyDataSetChanged();
    }

    protected class MyViewHolderCard extends RecyclerView.ViewHolder {
        private TextView textViewTitulo;
        private TextView textViewDescricao;
        private TextView textViewLocal;
        private TextView textViewHorario;
        private TextView textViewData;

        Button buttonExcluir; //= itemView.findViewById(R.id.btnExcluirCard);
        Button buttonEditar; // = itemView.findViewById(R.id.btnEditarCard);

        private MyViewHolderCard(final View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTituloEvento);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricaoEvento);
            textViewLocal = itemView.findViewById(R.id.textViewLocalEvento);
            textViewHorario = itemView.findViewById(R.id.textViewHorarioEvento);
            textViewData = itemView.findViewById(R.id.textViewDataEvento);

            buttonExcluir = itemView.findViewById(R.id.btnExcluirCard);
            buttonEditar = itemView.findViewById(R.id.btnEditarCard);

            /**/
//            if ( lcf != null) {
//                buttonExcluir = itemView.findViewById(R.id.btnExcluirCard);
//                buttonExcluir.setVisibility(View.INVISIBLE);
//                buttonEditar = itemView.findViewById(R.id.btnEditarCard);
//                buttonEditar.setVisibility(View.INVISIBLE);
//            }
            /**/

        }
    }


}
