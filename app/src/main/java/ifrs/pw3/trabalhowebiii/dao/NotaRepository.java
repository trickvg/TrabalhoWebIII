package ifrs.pw3.trabalhowebiii.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ifrs.pw3.trabalhowebiii.model.Nota;
import ifrs.pw3.trabalhowebiii.util.BDUtil;

public class NotaRepository {

    private BDUtil bdUtil;

    public NotaRepository(Context context){
        bdUtil = new BDUtil(context);
    }

    public String insert(String titulo_nota, String descricao_nota){
        ContentValues valores = new ContentValues();
        valores.put("TITULO_NOTA", titulo_nota);
        valores.put("DESCRICAO_NOTA", descricao_nota);
        long resultado = bdUtil.getConexao().insert("NOTA", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }

    public Integer delete(int codigo){
        Integer linhasAfetadas = bdUtil.getConexao().delete("NOTA","_id_nota = ?", new String[]{Integer.toString(codigo)});
        bdUtil.close();
        return linhasAfetadas;
    }

    public List<Nota> getAll(){
        List<Nota> notas = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT _ID_NOTA, TITULO_NOTA, DESCRICAO_NOTA ");
        stringBuilderQuery.append("FROM  NOTA ");
        stringBuilderQuery.append("ORDER BY _ID_NOTA");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Nota nota = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()){
            // Cria objetos do tipo tarefa
            nota =  new Nota();
            //adiciona os dados no objeto
            nota.set_id_nota(cursor.getInt(cursor.getColumnIndex("_ID_NOTA")));
            nota.setTitulo_nota(cursor.getString(cursor.getColumnIndex("TITULO_NOTA")));
            nota.setDescricao_nota(cursor.getString(cursor.getColumnIndex("DESCRICAO_NOTA")));
            //adiciona o objeto na lista
            notas.add(nota);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return notas;
    }
    public Nota getNota(int codigo){
        Cursor cursor =  bdUtil.getConexao().rawQuery("SELECT * FROM NOTA WHERE _ID_NOTA = "+ codigo,null);
        cursor.moveToFirst();
        Nota n = new Nota();
        n.set_id_nota(cursor.getInt(cursor.getColumnIndex("_ID_NOTA")));
        n.setTitulo_nota(cursor.getString(cursor.getColumnIndex("TITULO_NOTA")));
        n.setDescricao_nota(cursor.getString(cursor.getColumnIndex("DESCRICAO_NOTA")));
        bdUtil.close();
        return n;
    }

    public int update(Nota nota){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("TITULO_NOTA",       nota.getTitulo_nota());
        contentValues.put("DESCRICAO_NOTA",   nota.getDescricao_nota());
        //atualiza o objeto usando a chave
        int retorno = bdUtil.getConexao().update("NOTA", contentValues, "_id_nota = ?", new String[]{Integer.toString(nota.get_id_nota())});
        bdUtil.close();
        return retorno;
    }
}
