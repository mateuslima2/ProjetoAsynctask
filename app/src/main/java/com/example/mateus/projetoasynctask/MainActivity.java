package com.example.mateus.projetoasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.mateus.projetoasynctask.entity.Pessoa;
import com.example.mateus.projetoasynctask.listener.btListarListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvPessoas;
    Button btListar;
    Button btCadastrar;
    ArrayAdapter<Pessoa> adapter;
    List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPessoas = (ListView) findViewById(R.id.lvPessoas) ;
        btListar = (Button) findViewById(R.id.btListar);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);

        btListar.setOnClickListener(new btListarListener(this));
    }

    public ListView getLvPessoas() {
        return lvPessoas;
    }
    public void setLvPessoas(ListView lvPessoas) {
        this.lvPessoas = lvPessoas;
    }
    public Button getBtListar() {
        return btListar;
    }
    public void setBtListar(Button btListar) {
        this.btListar = btListar;
    }
    public Button getBtCadastrar() {
        return btCadastrar;
    }
    public void setBtCadastrar(Button btCadastrar) {
        this.btCadastrar = btCadastrar;
    }
    public ArrayAdapter<Pessoa> getAdapter() {
        return adapter;
    }
    public void setAdapter(ArrayAdapter<Pessoa> adapter) {
        this.adapter = adapter;
    }
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }



}
