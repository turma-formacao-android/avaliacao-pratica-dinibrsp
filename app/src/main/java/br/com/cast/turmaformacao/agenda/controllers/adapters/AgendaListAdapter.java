package br.com.cast.turmaformacao.agenda.controllers.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.R;

public class AgendaListAdapter extends BaseAdapter {

    private List<Agenda> agendaList;
    private Activity context;

    public AgendaListAdapter(Activity context, List<Agenda> agendaList) {
        this.context = context;
        this.agendaList = agendaList;
    }

    public void setDataValues(List<Agenda> values) {
        agendaList.clear();
        agendaList.addAll(values);
    }

    @Override
    public int getCount() {
        return agendaList.size();
    }

    @Override
    public Agenda getItem(int position) {
        return agendaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Agenda agenda = getItem(position);
        View taskListItemView = context.getLayoutInflater().inflate(R.layout.list_item_agenda, parent, false);


        TextView textViewName = (TextView) taskListItemView.findViewById(R.id.textViewName);
        textViewName.setText(agenda.getName());


        return taskListItemView;
    }
}

