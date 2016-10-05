package com.example.mateus.projetoasynctask.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mateus.projetoasynctask.entity.Pessoa;
/* ta dando erro no Gson */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Mateus on 03/10/2016.
 */
public class CadastrarPessoaAsyncTask extends AsyncTask<Pessoa, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Pessoa... pessoas) {

        try {

            Gson gson = new Gson();
            String pessoaJson = gson.toJson(pessoas[0]);

            Log.i("MainActivity", pessoaJson);

// enviar os dados
            URL url = new URL("ladoss.com.br:8080/meuservico");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            DataOutputStream wr = new DataOutputStream(
                    urlConnection.getOutputStream());

            wr.writeBytes(pessoaJson);
            wr.close();

        } catch (IOException e) {

            Log.e("CadastrarPessoa", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
