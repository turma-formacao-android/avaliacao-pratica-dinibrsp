package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.AgendaListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.persistence.AgendaReposiroty;
import br.com.cast.turmaformacao.agenda.model.services.AgendaBusinessService;

public class AgendaFilterActivity extends AppCompatActivity {


    private ListView listViewFilterList;
    private Agenda selectedAgenda;
    private EditText editTextName;
    private Button buttonFilter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        bindEditTextName();
        bindButtonFilter();
        bindAgendaList();
    }

    private void bindButtonFilter() {
        buttonFilter = (Button) findViewById(R.id.buttonFilter);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrar();
            }
        });
    }

    private void filtrar(){
        updateAgendaList();
    }


    private void bindAgendaList() {
        listViewFilterList = (ListView) findViewById(R.id.listViewFilterList);
        registerForContextMenu(listViewFilterList);
        listViewFilterList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AgendaListAdapter adapter = (AgendaListAdapter) listViewFilterList.getAdapter();
                selectedAgenda = adapter.getItem(position);
                return false;
            }
        });
    }


    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_agenda_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void updateAgendaList() {
        Agenda agendaFiltro = new Agenda();

        agendaFiltro.setName(editTextName.getText().toString());

        List<Agenda> values = AgendaReposiroty.getFiltro(agendaFiltro);

        listViewFilterList.setAdapter(new AgendaListAdapter(this, values));

        AgendaListAdapter adapter = (AgendaListAdapter) listViewFilterList.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(AgendaFilterActivity.this).setTitle("Certeza?").setMessage("Deletar").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgendaBusinessService.delete(selectedAgenda);
                selectedAgenda = null;
                updateAgendaList();
                Toast.makeText(AgendaFilterActivity.this, "Deletado", Toast.LENGTH_SHORT).show();
            }
        })
                .setNeutralButton("Nao", null).create().show();
    }

    private void onMenuEditClick() {
        Intent goToAgendaForm = new Intent(AgendaFilterActivity.this, AgendaFormActivity.class);
        goToAgendaForm.putExtra(AgendaFormActivity.PARAM_AGENDA, selectedAgenda);
        startActivity(goToAgendaForm);
    }

    public void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }


}
