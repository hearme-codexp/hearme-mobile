package br.senai.sp.informatica.mobile.apphearme.view;

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
    private Button btnBluetooth;
    private FloatingActionButton blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        itemLista = new HistoricoAdapter();
        listView = findViewById(R.id.lista);
        listView.setAdapter(itemLista);
        blue = findViewById(R.id.floatBlue);
    }

    public void shwMap(View view){
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void shwBlue(View view){
        startActivity(new Intent(this, BlueActivity.class));
    }

}
