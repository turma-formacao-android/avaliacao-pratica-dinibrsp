package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.persistence.AgendaReposiroty;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaBusinessService {


    private AgendaBusinessService() {
        super();
    }

    public static List<Agenda> findAll() {
        List<Agenda> agendas = AgendaReposiroty.getAll();
        return agendas;
    }


    public static void save(Agenda agenda) {
        AgendaReposiroty.save(agenda);
    }

    public static void delete(Agenda selectedAgenda) {
        AgendaReposiroty.delete(selectedAgenda.get_id());
    }

}
