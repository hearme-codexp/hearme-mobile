package br.senai.sp.informatica.mobile.apphearme.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.senai.sp.informatica.mobile.apphearme.R;
import br.senai.sp.informatica.mobile.apphearme.domain.ApiResponse;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.model.MyLocationListener;
import br.senai.sp.informatica.mobile.apphearme.service.HearmeRestService;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class BuscarDevices extends Activity implements AdapterView.OnItemClickListener{

    private static final int REQUEST_PERMISSAO_LOCATION = 1;
    private static final int REQUEST_ATIVAR_BLUETOOTH = 2;

    private static final String TAG = "BuscarDevices";
    private List<BluetoothDevice> lista = new ArrayList<>();
    private ListView listView;
    private ProgressDialog dialog;
    public BluetoothAdapter bluetoothAdapter;
    private HearmeRestService hearmeRestService;
    private MyLocationListener locationListener;
    private LocationManager locationManager;

    public BuscarDevices() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        hearmeRestService = new HearmeRestService();
        setContentView(R.layout.pesquisar_devices);
        listView = findViewById(R.id.blueList);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);




        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth não disponível neste aparelho", Toast.LENGTH_SHORT).show();
            finish();
        }

        this.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        this.registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        this.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case REQUEST_PERMISSAO_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    buscar();
                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }

    public void buscar(){
        if(bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.cancelDiscovery();
        }

        lista.clear();
        //listar também dispositivos conhecidos
        final Set<BluetoothDevice> conhecidos = bluetoothAdapter.getBondedDevices();
        lista.addAll(conhecidos);
        bluetoothAdapter.startDiscovery();
        dialog = ProgressDialog.show(this, "HearMe", "Buscando devices...", false, true);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        private int count;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                lista.add(device);
                count++;
                // Toast.makeText(context, "Encontrou: " + device.getName() + ":" + device.getAddress(), Toast.LENGTH_SHORT).show();
            }else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
                count = 0;
                // Toast.makeText(context, "Busca iniciada", Toast.LENGTH_SHORT).show();
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                // Toast.makeText(context, "Busca finalizada", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                updateLista();
            }
        }
    };

    protected void updateLista(){
        List<String> nomes = new ArrayList<String>();

        for(BluetoothDevice device : lista){
            boolean pareado = device.getBondState() == BluetoothDevice.BOND_BONDED;
            nomes.add(device.getName() + " - " + device.getAddress() + (pareado ? " - Pareado" : ""));
        }

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = lista.get(position);

        // falar com a pulseira
        comunicarComPulseira(device);
        
        // montar intent passando device para activity de comunicação com o back-end
        //Intent intent = new Intent(this, RegistrarHistoricoActivity.class);
        //intent.putExtra(EXTRA_DEVICE, device);
        //startActivity(new Intent(this, HomeActivity.class));
    }

    @SuppressLint("MissingPermission")
    private void comunicarComPulseira(BluetoothDevice device) {

        BluetoothConfiguration config = new BluetoothConfiguration();
        config.context = getApplicationContext();
        config.bluetoothServiceClass = BluetoothClassicService.class;
        config.bufferSize = 1024;
        config.characterDelimiter = '\n';
        config.deviceName = "NYMERIA";
        config.callListenersInMainThread = true;

        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        BluetoothService.init(config);

        final BluetoothService service = BluetoothService.getDefaultInstance();

        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            public int msgCount;

            @Override
            public void onDataRead(byte[] buffer, int length) {
                Log.d(TAG, String.format("onDataRead: %d", msgCount++));
                Historico novoHistorico = new Historico();
                novoHistorico.setClienteId(1);
                final Date currentTime = Calendar.getInstance().getTime();
                novoHistorico.setDataHorarioAlerta(currentTime);
                novoHistorico.setAlertaId(1);
                Location loc = locationListener.getLoc();
                if (loc != null) {
                    novoHistorico.setLat((float) loc.getLatitude());
                    novoHistorico.setLon((float) loc.getLongitude());
                } else {
                    novoHistorico.setLat(-23.5364722f);
                    novoHistorico.setLon(-46.6463212f);
                }
                getHearmeRestService().enviarDadoHistorico(novoHistorico, new ApiResponse<Void>() {
                    @Override
                    public void onSuccess(Void data) {
                        Toast.makeText(BuscarDevices.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "Erro ao enviar historico", t);
                    }
                });
            }

            @Override
            public void onStatusChange(BluetoothStatus status) {
                // Toast.makeText(getBaseContext(), "onStatusChange", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceName(String deviceName) {
                //Toast.makeText(getBaseContext(), "onDeviceName", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onToast(String message) {
                //Toast.makeText(getBaseContext(), "onToast", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataWrite(byte[] buffer) {
                //Toast.makeText(getBaseContext(), "onDataWrite", Toast.LENGTH_SHORT).show();
            }
        });

        service.connect(device);
    }

    protected void onDestroy(){
        super.onDestroy();
        if(bluetoothAdapter != null){
            bluetoothAdapter.cancelDiscovery();
        }
        this.unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bluetoothAdapter.isEnabled()) {
            // Toast.makeText(this, "Bluetooth está ligado", Toast.LENGTH_SHORT).show();
            checarPermissoesEBuscar();
        } else {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ATIVAR_BLUETOOTH);
        }
    }

    private void checarPermissoesEBuscar() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSAO_LOCATION);
        } else {
            buscar();
            locationListener = new MyLocationListener(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 8, locationListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ATIVAR_BLUETOOTH) {
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(this, "O bluetooth não foi ativado,", Toast.LENGTH_SHORT).show();
            } else {
                checarPermissoesEBuscar();
            }
        }
    }

    public HearmeRestService getHearmeRestService() {
        return hearmeRestService;
    }
}
