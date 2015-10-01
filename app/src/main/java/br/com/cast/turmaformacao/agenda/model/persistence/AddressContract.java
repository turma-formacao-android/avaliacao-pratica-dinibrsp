package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;
import br.com.cast.turmaformacao.taskmanager.model.entities.Task;

public final class AddressContract {

    public static final String TABLE = "address";
    public static final String ID = "id";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String CEP = "cep";
    public static final String LOGRADOURO = "logradouro";
    public static final String ESTADO = "estado";
    public static final String AGENDAID = "agenda_id";

    public static final String[] COLUNS = {ID, BAIRRO, CIDADE, CEP, LOGRADOURO, ESTADO, AGENDAID};


    private AddressContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(BAIRRO + " TEXT NOT NULL, ");
        create.append(CIDADE + " TEXT NOT NULL, ");
        create.append(CEP + " TEXT NOT NULL, ");
        create.append(LOGRADOURO + " TEXT NOT NULL, ");
        create.append(ESTADO + " TEXT NOT NULL, ");
        create.append(AGENDAID + " INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Address address) {
        ContentValues values = new ContentValues();
        values.put(AddressContract.ID, task.getId());
        values.put(TaskContract._ID, task.get_id());
        values.put(TaskContract.NAME, task.getName());
        values.put(TaskContract.DESCRIPTION, task.getDescription());
        values.put(TaskContract.LABELID, 1L);
        return values;
    }

    public static Task getTask(Cursor cursor) {
        Task task = new Task();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            task.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.ID)));
            task.set_id(cursor.getString(cursor.getColumnIndex(TaskContract._ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(TaskContract.NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(TaskContract.DESCRIPTION)));

            Label label = new Label();
            label.setId(cursor.getLong(cursor.getColumnIndex(TaskContract.LABELID)));
            task.setLabel(label);

            return task;
        }
        return null;
    }

    public static List<Task> getTasks(Cursor cursor) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            tasks.add(getTask(cursor));
        }
        return tasks;
    }

}
