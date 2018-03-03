package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.senai.sp.informatica.mobile.apphearme.R;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
    }

    public void btnEntrar(View view){
        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();

        startActivity(new Intent(this, HomeActivity.class));
        etLogin.setText(null);
        etSenha.setText(null);
    }

    public void btnCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
        etLogin.setText(null);
        etSenha.setText(null);
    }
}
