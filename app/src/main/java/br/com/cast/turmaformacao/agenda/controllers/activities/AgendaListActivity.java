package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.Toast;


import com.melnykov.fab.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.AgendaListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.services.AgendaBusinessService;

public class AgendaListActivity extends AppCompatActivity {


    private ListView listViewAgendaList;
    private Agenda selectedAgenda;
    private FloatingActionButton fab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);
        bindAgendaList();
        bindFloatingActionButton();
    }


    private void bindAgendaList() {
        listViewAgendaList = (ListView) findViewById(R.id.listViewAgendaList);
        registerForContextMenu(listViewAgendaList);
        listViewAgendaList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AgendaListAdapter adapter = (AgendaListAdapter) listViewAgendaList.getAdapter();
                selectedAgenda = adapter.getItem(position);
                return false;
            }
        });
    }


    protected void onResume() {
        updateAgendaList();
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_agenda_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_filter:
                onMenuFilterClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent redirectToAgendaFormActivity = new Intent(AgendaListActivity.this, AgendaFormActivity.class);
        startActivity(redirectToAgendaFormActivity);
    }

    private void onMenuFilterClick() {
        Intent redirectToFilterFormActivity = new Intent(AgendaListActivity.this, AgendaFilterActivity.class);
        startActivity(redirectToFilterFormActivity);
    }


    private void updateAgendaList() {
        List<Agenda> values = AgendaBusinessService.findAll();
        listViewAgendaList.setAdapter(new AgendaListAdapter(this, values));
        AgendaListAdapter adapter = (AgendaListAdapter) listViewAgendaList.getAdapter();
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
        new AlertDialog.Builder(AgendaListActivity.this).setTitle(R.string.lbl_confirm).setMessage(R.string.msg_delete).setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgendaBusinessService.delete(selectedAgenda);
                selectedAgenda = null;
                String message = getString(R.string.msg_delete_success);
                updateAgendaList();
                Toast.makeText(AgendaListActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        })
                .setNeutralButton(R.string.lbl_no, null).create().show();
    }

    private void onMenuEditClick() {
        Intent goToAgendaForm = new Intent(AgendaListActivity.this, AgendaFormActivity.class);
        goToAgendaForm.putExtra(AgendaFormActivity.PARAM_AGENDA, selectedAgenda);
        startActivity(goToAgendaForm);
    }

    private void bindFloatingActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.button_add);
        fab.attachToListView(listViewAgendaList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEstoqueFormActivity = new Intent(AgendaListActivity.this, AgendaFormActivity.class);
                startActivity(goToEstoqueFormActivity);
            }
        });
    }


}
