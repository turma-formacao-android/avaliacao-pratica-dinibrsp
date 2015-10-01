package br.com.cast.turmaformacao.agenda;


import android.app.Application;

import br.com.cast.turmaformacao.agenda.Util.ApplicationUtil;

public class AgendaApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());

    }
}
