package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;
import br.com.cast.turmaformacao.agenda.model.persistence.AgendaReposiroty;
import br.com.cast.turmaformacao.agenda.model.persistence.TelefoneReposiroty;

/**
 * Created by Administrador on 01/10/2015.
 */
public class TelefoneBusinessService {


    private TelefoneBusinessService() {
        super();
    }

    public static List<Telefone> findAll() {
        List<Telefone> telefones = TelefoneReposiroty.getAll();


        return telefones;
    }


    public static void save(Telefone telefone) {
        TelefoneReposiroty.save(telefone);
    }

    public static void delete(Telefone selectedTelefone) {
        TelefoneReposiroty.delete(selectedTelefone.get_id());
    }

}
