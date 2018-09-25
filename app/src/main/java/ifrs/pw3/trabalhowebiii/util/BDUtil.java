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
        StringBuilder criarTabelaUsuario = new StringBuilder();
        StringBuilder criarTabelaNota = new StringBuilder();

        criarTabelaNota.append(" CREATE TABLE NOTA(");
        criarTabelaNota.append(" _ID_NOTA       INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabelaNota.append(" TITULO_NOTA    TEXT NOT NULL,");
        criarTabelaNota.append(" DESCRICAO_NOTA TEXT NOT NULL)");

        criarTabelaUsuario.append(" CREATE TABLE USUARIO (");
        criarTabelaUsuario.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabelaUsuario.append(" NOME  TEXT    NOT NULL,");
        criarTabelaUsuario.append(" SENHA TEXT    NOT NULL)");


        db.execSQL(criarTabelaUsuario.toString());
        db.execSQL(criarTabelaNota.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        db.execSQL("DROP TABLE IF EXISTS NOTA");
        onCreate(db);
    }
//
    public SQLiteDatabase getConexao(){
        return this.getWritableDatabase();
    }
}
