package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import br.com.cast.turmaformacao.agenda.Util.FormHelper;
import br.com.cast.turmaformacao.agenda.controllers.adapters.TelefoneListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.model.persistence.AgendaReposiroty;
import br.com.cast.turmaformacao.agenda.model.services.AgendaBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.TelefoneBusinessService;

public class AgendaFormActivity extends AppCompatActivity {

    public static final String PARAM_AGENDA = "PARAM_AGENDA";
    private EditText editTextName;
    private EditText editTextTel;
    private EditText editTextCep;
    private EditText editTextMail;
    private EditText editTextSocial;
    private EditText editTextBairro;
    private EditText editTextRua;
    private EditText editTextCidade;
    private EditText editTextEstado;
    private EditText editTextTels;
    private Button buttonCheck;
    private List<Telefone> telefones;
    private Button buttonAddFone;
    private Agenda agenda;
    private Telefone telefone;
    private ListView listViewTelefoneList;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        initAgenda();
        bindEditTextName();
        bindEditTextTel();
        bindEditTextCep();
        bindEditTextMail();
        bindEditTextSocial();
        bindButtonCheck();
        bindEditTextRua();
        bindEditTextBairro();
        bindEditTextCidade();
        bindEditTextEstado();
        /*bindButtonAddFone();
        bindEditTextTels();*/
        //bindTelsList();
    }
    private void initAgenda() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.agenda = (Agenda) getIntent().getExtras().getParcelable(PARAM_AGENDA);
        } else {
            this.agenda = this.agenda == null ? new Agenda() : agenda;
        }
    }


    /*private void bindTelsList(){
        listViewTelefoneList = (ListView) findViewById(R.id.listViewTelefonesList);
        registerForContextMenu(listViewTelefoneList);
        listViewTelefoneList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TelefoneListAdapter adapter = (TelefoneListAdapter) listViewTelefoneList.getAdapter();
                telefone = adapter.getItem(position);
                return false;
            }
        });
    }*/

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_save:
                onMenuSaveClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveClick() {
        String requiredMessage = AgendaFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextName)) {
            binAgenda();
            AgendaBusinessService.save(agenda);
        }
    }

    private void binAgenda() {
        agenda.setName(editTextName.getText().toString());
        agenda.setTel(editTextTel.getText().toString());
        agenda.setMail(editTextMail.getText().toString());
        agenda.setSocial(editTextSocial.getText().toString());
        agenda.setCep(editTextCep.getText().toString());
        agenda.setLogradouro(editTextRua.getText().toString());
        agenda.setBairro(editTextBairro.getText().toString());
        agenda.setCidade(editTextCidade.getText().toString());
        agenda.setEstado(editTextEstado.getText().toString());

        finish();
    }


    public void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(agenda.getName() == null ? "" : agenda.getName());
    }

    /*public void bindEditTextTels() {
        editTextTels = (EditText) findViewById(R.id.editTextTels);
    }*/

    public void bindEditTextTel() {
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextTel.setText(agenda.getTel() == null ? "" : agenda.getTel());
    }

    public void bindEditTextCep() {
        editTextCep = (EditText) findViewById(R.id.editTextCep);
        editTextCep.setText(agenda.getCep() == null ? "" : agenda.getCep());
    }

    public void bindEditTextMail() {
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextMail.setText(agenda.getMail() == null ? "" : agenda.getMail());
    }

    public void bindEditTextSocial() {
        editTextSocial = (EditText) findViewById(R.id.editTextSocial);
        editTextSocial.setText(agenda.getSocial() == null ? "" : agenda.getSocial());
    }

    public void bindEditTextRua() {
        editTextRua = (EditText) findViewById(R.id.editTextRua);
        editTextRua.setText(agenda.getLogradouro() == null ? "" : agenda.getLogradouro());
    }

    public void bindEditTextBairro() {
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextBairro.setText(agenda.getBairro() == null ? "" : agenda.getBairro());
    }

    public void bindEditTextCidade() {
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextCidade.setText(agenda.getCidade() == null ? "" : agenda.getCidade());
    }

    public void bindEditTextEstado() {
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextEstado.setText(agenda.getEstado() == null ? "" : agenda.getEstado());
    }

    private void bindButtonCheck() {
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAddressAgenda().execute(editTextCep.getText().toString());
            }
        });
    }


    /*private void bindButtonAddFone() {
        buttonAddFone = (Button) findViewById(R.id.buttonAddFone);
        buttonAddFone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String required = AgendaFormActivity.this.getString(R.string.msg_required);
                if (!FormHelper.validateRequired(required, editTextTels)) {
                    Telefone telefone = new Telefone();

                    telefone.setNumero(editTextTels.getText().toString());
                    agenda.getTels().add(telefone);
                    TelefoneBusinessService.save(telefone);
                    editTextTels.setText("");
                    updateTelefoneList();
                }
            }
        });
    }*/

    private void binFone() {
        telefone.setNumero(editTextTel.getText().toString());
    }

    private void updateTelefoneList() {
        List<Telefone> values = TelefoneBusinessService.findAll();
        listViewTelefoneList.setAdapter(new TelefoneListAdapter(this, values));
        TelefoneListAdapter adapter = (TelefoneListAdapter) listViewTelefoneList.getAdapter();
        adapter.notifyDataSetChanged();
    }


    private class GetAddressAgenda extends AsyncTask<String, Void, Agenda> {

        @Override
        protected Agenda doInBackground(String... params) {
            return AddressService.getAddressByCep(params[0]);
        }

        @Override
        protected void onPostExecute(Agenda agenda) {
            super.onPostExecute(agenda);
            try{
                editTextRua.setText(agenda.getLogradouro().toString());
                editTextBairro.setText(agenda.getBairro().toString());
                editTextCidade.setText(agenda.getCidade().toString());
                editTextEstado.setText(agenda.getEstado().toString());
            }
            catch (Exception e){
                Toast.makeText(AgendaFormActivity.this, R.string.not_found, Toast.LENGTH_LONG).show();
            }
        }
    }


}
