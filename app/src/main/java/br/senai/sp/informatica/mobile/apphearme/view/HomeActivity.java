package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.senai.sp.informatica.mobile.apphearme.R;

public class HomeActivity extends AppCompatActivity{

    private ListView listView;
    private BaseAdapter itemLista;
    private Button map;
    private Button btBuscar;
    private Button btLigar;
    private final Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        itemLista = new HistoricoAdapter();
        listView = findViewById(R.id.lista);
        listView.setAdapter(itemLista);

        btBuscar = (Button) findViewById(R.id.btBuscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, BuscarDevices.class);
                startActivity(i);
            }
        });

        btLigar = (Button) findViewById(R.id.btLigar);
        btLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, BlueActivity.class);
                startActivity(i);
            }
        });

        map = findViewById(R.id.btnMap);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent j = new Intent(ctx, MapsActivity.class);
                startActivity(j);
            }
        });

    }

}
