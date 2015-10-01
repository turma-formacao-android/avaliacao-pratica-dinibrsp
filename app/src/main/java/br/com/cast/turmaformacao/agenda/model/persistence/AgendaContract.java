package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;


public class AgendaContract {

    public static final String TABLE = "agenda";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";

    public static final String[] COLUNS = {ID, NAME, ADDRESS};

    private AgendaContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(ADDRESS + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put(AgendaContract.ID, agenda.get_id());
        values.put(AgendaContract.NAME, agenda.getName());
        values.put(AgendaContract.ADDRESS, agenda.getAddress().get_id());
        return values;
    }

    public static Agenda getAgenda(Cursor cursor) {
        Agenda agenda = new Agenda();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(AgendaContract.ID)));
            agenda.setName(cursor.getString(cursor.getColumnIndex(AgendaContract.NAME)));

            Address address = new Address();
            address.set_id(cursor.getLong(cursor.getColumnIndex(AgendaContract.ADDRESS)));
            agenda.setAddress(address);

            return agenda;
        }
        return null;
    }

    public static List<Agenda> getAgendas(Cursor cursor) {
        ArrayList<Agenda> agendas = new ArrayList<>();
        while (cursor.moveToNext()) {
            agendas.add(getAgenda(cursor));
        }
        return agendas;
    }


}
