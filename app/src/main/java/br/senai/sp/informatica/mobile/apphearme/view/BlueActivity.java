package br.senai.sp.informatica.mobile.apphearme.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BlueActivity extends Activity {

    public BluetoothAdapter bluetoothAdapter;

    public BlueActivity(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        if(bluetoothAdapter == null){
            Toast.makeText(this, "Blue não disponível", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(bluetoothAdapter.isEnabled()){
            Toast.makeText(this, "Blue está ligado", Toast.LENGTH_SHORT).show();
        } else{
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 0);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != Activity.RESULT_OK){
            Toast.makeText(this, "Blue não foi ativado", Toast.LENGTH_SHORT).show();
        }
    }

}
