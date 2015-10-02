package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;


public final class AgendaReposiroty {

    private AgendaReposiroty(){
        super();
    }


    public static void save(Agenda agenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AgendaContract.getContentValues(agenda);
        if(agenda.get_id() == null) {
            db.insert(AgendaContract.TABLE, null, values);
        }else{
            String where = AgendaContract.ID + " = ? ";
            String[] params = {agenda.get_id().toString()};
            db.update(AgendaContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<Agenda> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUNS, null, null, null, null, AgendaContract.ID);
        List<Agenda> values = AgendaContract.getAgendas(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete (long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(AgendaContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Agenda> getFiltro(Agenda agenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.NAME + " = ? COLLATE NOCASE";
        String[] params = {agenda.getName()};

        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUNS, where, params, null, null, AgendaContract.ID);
        List<Agenda> values = AgendaContract.getAgendas(cursor);


        db.close();
        databaseHelper.close();
        return values;
    }


    public static Agenda getId(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.ID + " = ?";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUNS, where, params, null, null, AgendaContract.ID);
        Agenda agenda = AgendaContract.getAgenda(cursor);
        db.close();
        databaseHelper.close();

        return agenda;
    }
}
