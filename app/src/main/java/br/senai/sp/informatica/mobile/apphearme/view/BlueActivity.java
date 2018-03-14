package br.senai.sp.informatica.mobile.apphearme.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import br.senai.sp.informatica.mobile.apphearme.R;

public class BlueActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private Button swOnOff, btnlist, btnVisible;
    private BluetoothAdapter ba;
    private Set<BluetoothDevice>pairedDevices;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = bluetoothAdapter.getBondedDevices();
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);

        swOnOff = findViewById(R.id.swOnOff);
        btnlist = findViewById(R.id.btnList);
        btnVisible = findViewById(R.id.btnGetVisible);

        ba = BluetoothAdapter.getDefaultAdapter();
        listView = findViewById(R.id.blueList);
    }

    public void setSwOnOff(View view){
        if(!swOnOff.isActivated()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Blue on", Toast.LENGTH_SHORT).show();
        }
    }

    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View view){
        pairedDevices = ba.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice btDev : pairedDevices) list.add(btDev.getName());
        Toast.makeText(getApplicationContext(), "Lista dev pareados", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

}
