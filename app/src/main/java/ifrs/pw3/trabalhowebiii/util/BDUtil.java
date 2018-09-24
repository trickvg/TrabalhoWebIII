package ifrs.pw3.trabalhowebiii.util;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class BDUtil extends SQLiteOpenHelper {

    private static final String BASE_DADOS = "EVENTOS";
    private static final int VERSAO = 1;

    public BDUtil(Context context) {
        super(context,BASE_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder criarTabela = new StringBuilder();
        criarTabela.append(" CREATE TABLE NOTA (");
        criarTabela.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabela.append(" NOME  TEXT    NOT NULL,");
        criarTabela.append(" SENHA TEXT    NOT NULL)");
//        criarTabela.append(" TELEFONE   TEXT NOT NULL)");
        db.execSQL(criarTabela.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS NOTA");
        onCreate(db);
    }
//
    public SQLiteDatabase getConexao(){
        return this.getWritableDatabase();
    }
}
