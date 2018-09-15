package ifrs.pw3.trabalhowebiii.dao;

import android.content.ContentValues;
import android.content.Context;

import ifrs.pw3.trabalhowebiii.util.BDUtil;

public class PessoaRepository {

    private BDUtil bdUtil;

    public PessoaRepository(Context context){
        bdUtil = new BDUtil(context);
    }

    public String insert(String nome, String senha){
        ContentValues valores = new ContentValues();
        valores.put("NOME", nome);
        valores.put("SENHA", senha);
        long resultado = bdUtil.getConexao().insert("NOTA", null, valores);
        if (resultado ==-1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Registro Inserido com sucesso";
    }

}
