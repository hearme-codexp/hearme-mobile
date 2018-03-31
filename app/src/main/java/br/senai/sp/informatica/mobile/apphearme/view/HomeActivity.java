package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import br.senai.sp.informatica.mobile.apphearme.R;

public class HomeActivity extends AppCompatActivity{

    private ListView listView;
    private BaseAdapter itemLista;
    private Button map;
    private Button blue;
    private final Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        itemLista = new HistoricoAdapter();
        listView = findViewById(R.id.lista);
        listView.setAdapter(itemLista);

        blue = findViewById(R.id.btnBlue);
        blue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
