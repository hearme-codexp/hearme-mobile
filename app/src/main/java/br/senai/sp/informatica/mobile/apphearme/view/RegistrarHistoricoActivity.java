package br.senai.sp.informatica.mobile.apphearme.view;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothClassicService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothConfiguration;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus;
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothWriter;

import java.util.UUID;

import br.senai.sp.informatica.mobile.apphearme.domain.ApiResponse;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.service.HearmeRestService;

public class RegistrarHistoricoActivity extends Activity {
    public final String TAG = "RegistrarHistorico";

    int msgCount = 0;
    private HearmeRestService hearmeRestService;

    @Override
    public void onCreate(Bundle savedInstanceState){
        hearmeRestService = new HearmeRestService();

        super.onCreate(savedInstanceState);
        BluetoothDevice device = (BluetoothDevice) getIntent().getExtras().get(BuscarDevices.EXTRA_DEVICE);

        BluetoothConfiguration config = new BluetoothConfiguration();
        config.context = getApplicationContext();
        config.bluetoothServiceClass = BluetoothClassicService.class;
        config.bufferSize = 1024;
        config.characterDelimiter = '\n';
        config.deviceName = "NYMERIA";
        config.callListenersInMainThread = true;

        // Bluetooth Classic
        config.uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); // Set null to find all devices on scan.
        BluetoothService.init(config);

        final BluetoothService service = BluetoothService.getDefaultInstance();

        service.setOnEventCallback(new BluetoothService.OnBluetoothEventCallback() {
            @Override
            public void onDataRead(byte[] buffer, int length) {
                Log.d(TAG, String.format("onDataRead: %d", msgCount));
                Historico novoHistorico = new Historico();
                novoHistorico.setClienteId(1);
                // TODO: pegar data de verdade, ver se JSON aceita java.util.Date
                novoHistorico.setDataHorarioAlerta("Data falsa");
                // TODO: GPS
                novoHistorico.setLat(120);
                novoHistorico.setLon(80);
                getHearmeRestService().enviarDadoHistorico(novoHistorico, new ApiResponse<Historico>() {
                    @Override
                    public void onSuccess(Historico data) {
                        Toast.makeText(RegistrarHistoricoActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "Erro ao enviar historico", t);
                    }
                });
            }

            @Override
            public void onStatusChange(BluetoothStatus status) {
                Toast.makeText(getBaseContext(), "onStatusChange", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceName(String deviceName) {
                Toast.makeText(getBaseContext(), "onDeviceName", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onToast(String message) {
                Toast.makeText(getBaseContext(), "onToast", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataWrite(byte[] buffer) {
                Toast.makeText(getBaseContext(), "onDataWrite", Toast.LENGTH_SHORT).show();
            }
        });

        service.connect(device);
    }

    @NonNull
    private HearmeRestService getHearmeRestService() {
        return hearmeRestService;
    }
}
