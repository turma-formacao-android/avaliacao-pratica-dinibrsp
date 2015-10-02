package br.com.cast.turmaformacao.agenda.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.agenda.model.entities.Agenda;

public class AddressService {

    private static final String URL = "http://api.postmon.com.br/v1/cep/";

    private AddressService(){
        super();
    }

    public static Agenda getAddressByCep(String zipCode){
        Agenda agenda = null;

        try {
            java.net.URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getAddressByCep", "Código de retorno da requisicao de cep: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                agenda = objectMapper.readValue(inputStream, Agenda.class);

            }

        } catch (Exception e) {
            Log.e(AddressService.class.getName(), "" + e.getMessage());
        }
        return agenda;
    }


}
