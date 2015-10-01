package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Agenda;

public class AgendaFormActivity extends AppCompatActivity {

    public static final String PARAM_AGENDA = "PARAM_AGENDA";
    private EditText editTextName;
    private EditText editTextTel;
    private EditText editTextMail;
    private EditText editTextSocial;
    private Button buttonFriendAddress;
    private Agenda agenda;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        initAgenda();
        bindEditTextName();
        bindEditTextTel();
        bindEditTextMail();
        bindEditTextSocial();
        bindButtonFriendAddress();


    }
    private void initAgenda() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.agenda = (Agenda) getIntent().getExtras().getParcelable(PARAM_AGENDA);
        } else {
            this.agenda = this.agenda == null ? new Agenda() : agenda;
        }
    }

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
        Toast.makeText(AgendaFormActivity.this, "Teste", Toast.LENGTH_SHORT).show();
    }


    public void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(agenda.getName() == null ? "" : agenda.getName());
    }

    public void bindEditTextTel() {
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextTel.setText(agenda.getTels().toString() == null ? "" : agenda.getTels().toString());
    }

    public void bindEditTextMail() {
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextMail.setText(agenda.getMails().toString() == null ? "" : agenda.getMails().toString());
    }

    public void bindEditTextSocial() {
        editTextSocial = (EditText) findViewById(R.id.editTextSocial);
        editTextSocial.setText(agenda.getSocials() == null ? "" : agenda.getSocials().toString());
    }

    private void bindButtonFriendAddress() {
        buttonFriendAddress = (Button) findViewById(R.id.buttonFriendAddress);
        buttonFriendAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToAddFriendAddresses = new Intent(AgendaFormActivity.this, AgendaFormActivity.class);
                startActivity(redirectToAddFriendAddresses);
            }
        });
    }

}
