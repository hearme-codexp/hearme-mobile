package br.senai.sp.informatica.mobile.apphearme.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import br.senai.sp.informatica.mobile.apphearme.model.Device;

public class BlueActivity extends AppCompatActivity {

    private Button btnOnOff, btnlist, btnVisible;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    ListView listView;
    ArrayList list = new ArrayList();
    protected static final int DISCOVERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        btnOnOff = findViewById(R.id.btnOnOff);
        btnlist = findViewById(R.id.btnList);
        btnVisible = findViewById(R.id.btnGetVisible);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = findViewById(R.id.blueList);
    }

    public void setbtnOnOff(View view) {
        if(!btnOnOff.isActivated()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Blue on", Toast.LENGTH_SHORT).show();
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            list.add(device.getName() + "\n" + device.getAddress());
        }
    };

    public void list(View view) {
        //pairedDevices = bluetoothAdapter.getBondedDevices();
        String scanModeChanged = BluetoothAdapter.ACTION_SCAN_MODE_CHANGED;
        String beDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, filter);
        startActivityForResult(new Intent(beDiscoverable), DISCOVERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DISCOVERY_REQUEST) {
            Toast.makeText(this, "Discovery in progress", Toast.LENGTH_SHORT).show();
            findDevices();
        }
    }

    private void findDevices() {
        /*pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice btDev : pairedDevices) list.add(btDev.getName());
        Toast.makeText(getApplicationContext(), "Lista dev pareados", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);*/

        String lastUsedRemoteDevice = getLastUsedRemoteBTDevice();
        if(lastUsedRemoteDevice != null){
            String toastText = "Checking for known paired devices, namely: " + lastUsedRemoteDevice;
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            pairedDevices = bluetoothAdapter.getBondedDevices();

            for(BluetoothDevice btDev : pairedDevices){
                if(btDev.getAddress().equals(lastUsedRemoteDevice)){
                    Toast.makeText(getApplicationContext(), "Lista dev pareados", Toast.LENGTH_SHORT).show();
                    list.add(btDev.getName());
                }
            }
        }

        if(list == null){
            Toast.makeText(this, "starting discovery for remote devices", Toast.LENGTH_SHORT).show();
                if(bluetoothAdapter.startDiscovery()){
                    Toast.makeText(this, "Scanning for devices", Toast.LENGTH_SHORT).show();
                    registerReceiver(discovery_result, new IntentFilter(BluetoothDevice.ACTION_FOUND));
                }
        }

    }

    BroadcastReceiver discovery_result = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                list.add(device.getName() + "\n" + device.getAddress());
        }
    };

    private String getLastUsedRemoteBTDevice(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String result = preferences.getString("LAST_REMOTE_DEVICE_ADDRESS", null);
        return result;
    }
}