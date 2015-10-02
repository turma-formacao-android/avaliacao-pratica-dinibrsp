package br.com.cast.turmaformacao.agenda.controllers.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;

public class TelefoneListAdapter extends BaseAdapter {

    private List<Telefone> agendaTelefone;
    private Activity context;

    public TelefoneListAdapter(Activity context, List<Telefone> agendaTelefone) {
        this.context = context;
        this.agendaTelefone = agendaTelefone;
    }

    public void setDataValues(List<Telefone> values) {
        agendaTelefone.clear();
        agendaTelefone.addAll(values);
    }

    @Override
    public int getCount() {
        return agendaTelefone.size();
    }

    @Override
    public Telefone getItem(int position) {
        return agendaTelefone.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Telefone telefone = getItem(position);
        View telefoneListItemView = context.getLayoutInflater().inflate(R.layout.list_item_telefone, parent, false);


        TextView textViewNumero = (TextView) telefoneListItemView.findViewById(R.id.textViewNumero);
        textViewNumero.setText(telefone.getNumero());


        return telefoneListItemView;
    }
}

