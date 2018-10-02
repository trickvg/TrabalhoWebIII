package ifrs.pw3.trabalhowebiii.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.adapter.LinhaConsultaAdapter;
import ifrs.pw3.trabalhowebiii.dao.NotaRepository;
import ifrs.pw3.trabalhowebiii.model.Nota;

public class ListNotasActivity extends AppCompatActivity {
    private ListView listNotas;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notas);
        listNotas = this.findViewById(R.id.listViewNotas);
        this.getAll();
    }
    protected void getAll(){
        NotaRepository notaRepository = new NotaRepository(this);
        List<Nota> notas = notaRepository.getAll();
        listNotas.setAdapter(new LinhaConsultaAdapter(this, notas));
    }
}
