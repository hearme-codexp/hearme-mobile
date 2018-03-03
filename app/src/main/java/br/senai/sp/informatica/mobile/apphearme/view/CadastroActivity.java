package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.senai.sp.informatica.mobile.apphearme.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        etNome = findViewById(R.id.etCNome);
        etEmail = findViewById(R.id.etCEmail);
        etSenha = findViewById(R.id.etCSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);

    }

    public void btnCEntrar (View view){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

}
