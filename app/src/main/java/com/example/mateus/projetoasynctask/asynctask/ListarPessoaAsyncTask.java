package com.example.mateus.projetoasynctask.asynctask;

/**
 * Created by Mateus on 03/10/2016.
 */

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.example.mateus.projetoasynctask.entity.Pessoa;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;

public class ListarPessoaAsyncTask extends AsyncTask<String,Void,List<Pessoa>> {
    String baseUrl = "http://ladoss.info:8773/Convite_SERVICE/convidado";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Pessoa> doInBackground(String... params) {
        String urlString = params[0];
        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            InputStream instream = connection.getInputStream();

            JsonReader reader = new JsonReader(new InputStreamReader(instream, "UTF-8"));

            List<Pessoa> pessoas = getPessoas(reader);
            instream.close();

            return pessoas;
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;

    }


    public List<Pessoa> getPessoas(JsonReader reader) throws IOException {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        reader.beginArray();
        while (reader.hasNext()) {
            pessoas.add(getPessoa(reader));
        }
        reader.endArray();
        return pessoas;
    }

    public Pessoa getPessoa(JsonReader reader) throws IOException {

        String nome = null;
        String email = null;
        String endereco = null;
        String cpf = null;

        reader.beginObject();

        //iterando pelo json
        while (reader.hasNext()) {
            //name chave do json
            String name = reader.nextName();
            if (name.equals("nome")) {
                nome = reader.nextString();
            } else if (name.equals("email")) {
                email = reader.nextString();
            } else if (name.equals("endereco")) {
                endereco = reader.nextString();
            } else if (name.equals("user")) {
                cpf = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Pessoa(nome, email,endereco, cpf);
    }

    @Override
    protected void onPostExecute(List<Pessoa> result){
        super.onPostExecute(result);
    }
}
