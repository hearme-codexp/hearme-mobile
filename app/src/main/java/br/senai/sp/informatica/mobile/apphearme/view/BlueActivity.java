package br.senai.sp.informatica.mobile.apphearme.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Set;

import br.senai.sp.informatica.mobile.apphearme.R;

public class BlueActivity extends AppCompatActivity {

    private Button swOnOff, btnlist, btnVisible;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice>pairedDevices;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        swOnOff = findViewById(R.id.swOnOff);
        btnlist = findViewById(R.id.btnList);
        btnVisible = findViewById(R.id.btnGetVisible);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = findViewById(R.id.blueList);
    }

    public void setSwOnOff(View view){
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);
        Toast.makeText(getApplicationContext(), "Blue on", Toast.LENGTH_SHORT).show();
    }

    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View view){
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice btDev : pairedDevices) list.add(btDev.getName());
        Toast.makeText(getApplicationContext(), "Lista dev pareados", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                ArrayList list = new ArrayList();
                list.add(device.getName());
                final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                        android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
        }
    };

    public void metScan(LayoutInflater inflater, ViewGroup container, View view){
        view = inflater.inflate(R.layout.bluetooth, container, false);
        ToggleButton scan = view.findViewById(R.id.tgScan);
        scan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                if(isChecked){
                    bluetoothAdapter.startDiscovery();
                }else{
                    bluetoothAdapter.cancelDiscovery();
                }
            }
        });
    }

}
