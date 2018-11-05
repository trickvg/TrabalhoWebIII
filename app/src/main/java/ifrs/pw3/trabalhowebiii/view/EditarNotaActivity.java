package ifrs.pw3.trabalhowebiii.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import ifrs.pw3.trabalhowebiii.R;
import ifrs.pw3.trabalhowebiii.dao.NotaRepository;
import ifrs.pw3.trabalhowebiii.model.Nota;

public class EditarNotaActivity extends AppCompatActivity {
    private int _id_nota = 0;
    EditText editTextTitulo ;
    EditText editTextDesc;
    private EditText editTextData;
    Button botaoEditar ;
    private DatePickerDialog datePickerDialogData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nota);
        editTextTitulo =  this.findViewById(R.id.editTextTituloNota);
        editTextDesc =  this.findViewById(R.id.editTextDescricaoNota);
      //  editTextData = (EditText) this.findViewById(R.id.editTextDataEdit);
        botaoEditar =  this.findViewById(R.id.btnEditarNota);

        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual   = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual   = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual   = calendarDataAtual.get(Calendar.DAY_OF_MONTH);

        datePickerDialogData = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
                //formata o mês com dois digitos
                String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ? "0" + (mesSelecionado + 1 ): String.valueOf(mesSelecionado));
                editTextData.setText(diaSelecionado + "/" + mes + "/" + anoSelecionado);
            }}, anoAtual, mesAtual, diaAtual);


        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogData.show();
            }
        });

        //cria evento para o botão editar
        botaoEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alterar();
            }
        });

        NotaRepository notaRepository = new NotaRepository(this);

        //Pega o id do objeto que foi passado como parâmetro
        Bundle extra =  this.getIntent().getExtras();
        _id_nota = extra.getInt("_id_nota");

        //Pega o bojeto usando o id
        Nota nota = notaRepository.getNota(_id_nota);

        editTextTitulo.setText(nota.getTitulo_nota());
        editTextDesc.setText(nota.getDescricao_nota());
//        editTextData.setText(tarefa.getData());
    }
    private void alterar(){
        if(editTextTitulo.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Titulo obrigatório!", Toast.LENGTH_LONG).show();
            editTextTitulo.requestFocus();
        }
        else if(editTextDesc.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Descrição obrigatória!", Toast.LENGTH_LONG).show();
            editTextDesc.requestFocus();
        }
        else if(editTextData.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Data é obrigatório!", Toast.LENGTH_LONG).show();
            editTextData.requestFocus();
        }
        else {
            Nota nota = new Nota();
            nota.set_id_nota(_id_nota);
            nota.setTitulo_nota(editTextTitulo.getText().toString().trim());
            nota.setDescricao_nota(editTextDesc.getText().toString().trim());
//            tarefa.setData(editTextData.getText().toString().trim());
            int linhasAfetadas = new NotaRepository(this).update(nota);
            String msg = "Registro alterado com sucesso! ";
            if(linhasAfetadas == 0 ) msg = "Registro não foi alterado! ";
            //mostrando caixa de diálogo de sucesso
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(msg);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Forçando que o código retorne para a tela de consulta
                    Intent intent = new Intent(getApplicationContext(), ListNotasActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.show();
        }
    }
}
