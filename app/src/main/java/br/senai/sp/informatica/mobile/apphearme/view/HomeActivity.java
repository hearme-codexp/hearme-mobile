package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        itemLista = new HistoricoAdapter();
        listView = findViewById(R.id.lista);
        listView.setAdapter(itemLista);
        btnBluetooth = findViewById(R.id.btnBlue);
    }

    public void btnBluetooth(View view){
            startActivity(new Intent(this, BlueActivity.class));
    }

    public void shwMap(View view){
        startActivity(new Intent(this, MapsActivity.class));
    }

}
