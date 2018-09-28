package ifrs.pw3.trabalhowebiii.dao;

import android.content.ContentValues;
import android.content.Context;

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
}
