package br.senai.sp.informatica.mobile.apphearme.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import br.senai.sp.informatica.mobile.apphearme.R;
import br.senai.sp.informatica.mobile.apphearme.config.RetrofitConfig;
import br.senai.sp.informatica.mobile.apphearme.model.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText Nome;
    private EditText Email;
    private EditText etSenha;
    private EditText ConfirmarSenha;
    private Button btnEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);

    }
    public void btnCadastrar(View view) {
        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
        etLogin.setText(null);
        etSenha.setText(null);
    }

    public void btnEntrar(View view){

        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        if(login.equals("")|| senha.equals("")){
            Toast.makeText(this,"Campos não preenchidos", Toast.LENGTH_SHORT).show();
        }
        else if(login.equals("adm@adm.com") && senha.equals("123")){
            startActivity(new Intent(this, HomeActivity.class));
            etLogin.setText(null);
            etSenha.setText(null);
        }else{
            Toast.makeText(this,"Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
        }

    }
}
