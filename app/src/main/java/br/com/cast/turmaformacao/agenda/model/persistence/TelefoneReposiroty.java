package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;



import br.com.cast.turmaformacao.agenda.model.entities.Telefone;


public final class TelefoneReposiroty {

    private TelefoneReposiroty(){
        super();
    }


    public static void save(Telefone telefone){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = TelefoneContract.getContentValues(telefone);
        if(telefone.get_id() == null) {
            db.insert(TelefoneContract.TABLE, null, values);
        }else{
            String where = TelefoneContract.ID + " = ? ";
            String[] params = {telefone.get_id().toString()};
            db.update(TelefoneContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<Telefone> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TelefoneContract.TABLE, TelefoneContract.COLUNS, null, null, null, null, TelefoneContract.ID);
        List<Telefone> values = TelefoneContract.getTelefones(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TelefoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TelefoneContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

}
