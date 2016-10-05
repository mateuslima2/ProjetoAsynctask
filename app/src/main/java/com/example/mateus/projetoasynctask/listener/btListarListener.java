package com.example.mateus.projetoasynctask.listener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.mateus.projetoasynctask.MainActivity;
import com.example.mateus.projetoasynctask.asynctask.ListarPessoaAsyncTask;
import com.example.mateus.projetoasynctask.entity.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Mateus on 03/10/2016.
 */
public class btListarListener implements View.OnClickListener {
    MainActivity mainActivity;

    public btListarListener(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {

        List<Pessoa> pessoas =new ArrayList<Pessoa>();
        ListarPessoaAsyncTask listPessoa = new ListarPessoaAsyncTask();
        ListView lvPessoas = mainActivity.getLvPessoas();

        try {
            pessoas = listPessoa.execute("http://www.json-generator.com/api/json/get/cppcjEhaTC?indent=2").get();
            ArrayAdapter<Pessoa> adapter= new ArrayAdapter<Pessoa>(this.mainActivity,
                    android.R.layout.simple_list_item_1, pessoas);
            lvPessoas.setAdapter(adapter);

            mainActivity.setPessoas(pessoas);
            mainActivity.setAdapter(adapter);
            mainActivity.setLvPessoas(lvPessoas);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
