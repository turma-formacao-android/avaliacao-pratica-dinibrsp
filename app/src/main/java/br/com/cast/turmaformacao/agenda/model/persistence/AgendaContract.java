package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;


public class AgendaContract {

    public static final String TABLE = "agenda";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TEL = "tel";
    public static final String EMAIL = "email";
    public static final String SOCIAL = "social";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String CEP = "cep";
    public static final String LOGRADOURO = "logradouro";
    public static final String ESTADO = "estado";

    public static final String[] COLUNS = {ID, NAME, TEL, EMAIL, SOCIAL, BAIRRO, CIDADE, CEP, LOGRADOURO, ESTADO};

    private AgendaContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(TEL + " TEXT, ");
        create.append(EMAIL + " TEXT, ");
        create.append(SOCIAL + " TEXT, ");
        create.append(BAIRRO + " TEXT, ");
        create.append(CIDADE + " TEXT, ");
        create.append(CEP + " TEXT NOT NULL, ");
        create.append(LOGRADOURO + " TEXT, ");
        create.append(ESTADO + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put(AgendaContract.ID, agenda.get_id());
        values.put(AgendaContract.NAME, agenda.getName());
        values.put(AgendaContract.TEL, agenda.getTel());
        values.put(AgendaContract.EMAIL, agenda.getMail());
        values.put(AgendaContract.SOCIAL, agenda.getSocial());
        values.put(AgendaContract.BAIRRO, agenda.getBairro());
        values.put(AgendaContract.CIDADE, agenda.getCidade());
        values.put(AgendaContract.CEP, agenda.getCep());
        values.put(AgendaContract.LOGRADOURO, agenda.getLogradouro());
        values.put(AgendaContract.ESTADO, agenda.getEstado());
        return values;
    }

    public static Agenda getAgenda(Cursor cursor) {
        Agenda agenda = new Agenda();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(AgendaContract.ID)));
            agenda.setName(cursor.getString(cursor.getColumnIndex(AgendaContract.NAME)));
            agenda.setTel(cursor.getString(cursor.getColumnIndex(AgendaContract.TEL)));
            agenda.setMail(cursor.getString(cursor.getColumnIndex(AgendaContract.EMAIL)));
            agenda.setSocial(cursor.getString(cursor.getColumnIndex(AgendaContract.SOCIAL)));
            agenda.setBairro(cursor.getString(cursor.getColumnIndex(AgendaContract.BAIRRO)));
            agenda.setCidade(cursor.getString(cursor.getColumnIndex(AgendaContract.CIDADE)));
            agenda.setCep(cursor.getString(cursor.getColumnIndex(AgendaContract.CEP)));
            agenda.setLogradouro(cursor.getString(cursor.getColumnIndex(AgendaContract.LOGRADOURO)));
            agenda.setEstado(cursor.getString(cursor.getColumnIndex(AgendaContract.ESTADO)));


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
