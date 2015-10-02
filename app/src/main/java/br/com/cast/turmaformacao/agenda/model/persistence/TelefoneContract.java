package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;


public class TelefoneContract {

    public static final String TABLE = "telefone";
    public static final String ID = "id";
    public static final String TELEFONE = "telefone";
    public static final String CONTATO_ID = "contato_id";

    public static final String[] COLUNS = {ID, TELEFONE};

    private TelefoneContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(TELEFONE + " TEXT ");
        //create.append(CONTATO_ID + " INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Telefone telefone) {
        ContentValues values = new ContentValues();
        values.put(TelefoneContract.ID, telefone.get_id());
        values.put(TelefoneContract.TELEFONE, telefone.getNumero());
        //values.put(TelefoneContract.CONTATO_ID, telefone.getAgenda().get_id());
        return values;
    }

    public static Telefone getTelefone(Cursor cursor) {
        Telefone telefone = new Telefone();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            telefone.set_id(cursor.getLong(cursor.getColumnIndex(TelefoneContract.ID)));
            telefone.setNumero(cursor.getString(cursor.getColumnIndex(TelefoneContract.TELEFONE)));

            /*Agenda agenda = new Agenda();
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(TelefoneContract.CONTATO_ID)));
            telefone.setAgenda(agenda);*/

            return telefone;
        }
        return null;
    }

    public static List<Telefone> getTelefones(Cursor cursor) {
        ArrayList<Telefone> telefones = new ArrayList<>();
        while (cursor.moveToNext()) {
            telefones.add(getTelefone(cursor));
        }
        return telefones;
    }


}
