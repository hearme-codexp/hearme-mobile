package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.senai.sp.informatica.mobile.apphearme.R;

public class HomeActivity extends AppCompatActivity{

    private ListView listView;
    private BaseAdapter itemLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        itemLista = new HistoricoAdapter();
        listView = findViewById(R.id.lista);
        listView.setAdapter(itemLista);
    }

}
