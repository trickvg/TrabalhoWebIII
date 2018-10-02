package ifrs.pw3.trabalhowebiii.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.dao.NotaRepository;
import ifrs.pw3.trabalhowebiii.model.Nota;
import ifrs.pw3.trabalhowebiii.view.EditarNotaActivity;
import ifrs.pw3.trabalhowebiii.view.ListNotasActivity;


public class LinhaConsultaAdapter extends BaseAdapter {

    //Cria objeto LayoutInflater para ligar com a View activity_linha.xml
    private static LayoutInflater layoutInflater = null;

    List<Nota> notas = new ArrayList<>();
    NotaRepository notaRepository;

    //Cria objeto do tipo que lista as tarefas
    private ListNotasActivity listarAtividades;

    //Construtor que recebe a ativida como parametro e a lista de tarefas que vai retornar do BD
    public LinhaConsultaAdapter(ListNotasActivity listarAtividades, List<Nota> notas) {
        this.notas = notas;
        this.listarAtividades = listarAtividades;
        this.layoutInflater = (LayoutInflater) this.listarAtividades.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        notaRepository = new NotaRepository(listarAtividades);
    }

    //Retorna a quantidade de objetos que esta na lista
    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método converte os valores de um item  da lista de Tarefas para uma linha do ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Cria um objeto para acessar o layout activity_linha.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha, null);

        //vincula os campos do arquivo de layout aos objetos cadastrados
        TextView textViewTitulo = (TextView) viewLinhaLista.findViewById(R.id.textViewTitulo);
//        TextView textViewNome  = (TextView) viewLinhaLista.findViewById(R.id.textViewNome);
        TextView textViewDescricao = (TextView) viewLinhaLista.findViewById(R.id.textViewDescricao);
//        TextView textViewData = (TextView) viewLinhaLista.findViewById(R.id.textViewData);
        Button buttonExcluir = (Button) viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button buttonEditar = (Button) viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewTitulo.setText(String.valueOf(notas.get(position).getTitulo_nota()));
//        textViewNome.setText(tarefas.get(position).getNome());
        textViewDescricao.setText(notas.get(position).getDescricao_nota());
//        textViewData.setText(tarefas.get(position).getData());

        //Criando evento para excluir um registro do BD
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Registro excluído com sucesso!";
                Integer retorno = notaRepository.delete(notas.get(position).get_id_nota());
                if (retorno == 0)
                    mensagem = "Erro ao excluir registro!";
                Toast.makeText(listarAtividades, mensagem, Toast.LENGTH_LONG).show();
                atualizaLista();
            }
        });

        //Criando evento para editar um registro do BD
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listarAtividades, EditarNotaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("_id_nota", notas.get(position).get_id_nota());
                listarAtividades.startActivity(intent);
            }
        });
        return viewLinhaLista;
    }

    //atualizando a lista após excluir registro
    public void atualizaLista() {
        this.notas.clear();
        this.notas = notaRepository.getAll();
        this.notifyDataSetChanged();
    }
}