package ifrs.pw3.trabalhowebiii.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ifrs.pw3.trabalhowebiii.R;

import ifrs.pw3.trabalhowebiii.model.Evento;
import ifrs.pw3.trabalhowebiii.view.ClickRecycler;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        public static ClickRecycler clickRecycler;
        Context contexto;
        private List<Evento> listaEventos;

        public MyAdapter(Context ctx, List<Evento> list, ClickRecycler clickRecycler) {
            this.contexto = ctx;
            this.listaEventos = list;
            MyAdapter.clickRecycler = clickRecycler;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            System.out.println("Contexto="+viewGroup.getContext());
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_itens_recycler, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder,final int position) {
            Evento evento = listaEventos.get(position);
            viewHolder.viewNomeCidadeDesc.setText(evento.getTitulo_evento() + "\n" + evento.getData_evento()+ "\n" + evento.getDescricao_evento()+ "\n" + evento.getLocal_evento());
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
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

        protected class MyViewHolder extends RecyclerView.ViewHolder {

            protected TextView viewNomeCidadeDesc;
            protected ImageButton delete;

            public MyViewHolder(final View itemView) {
                super(itemView);
                viewNomeCidadeDesc = itemView.findViewById(R.id.line_text);
                delete = itemView.findViewById(R.id.line_delete);

                //Setup the click listener
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickRecycler.onCustomClick(listaEventos.get(getLayoutPosition()));
                    }
                });
            }
        }
    }
