package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.informatica.mobile.apphearme.R;
import br.senai.sp.informatica.mobile.apphearme.config.RetrofitConfig;
import br.senai.sp.informatica.mobile.apphearme.model.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etEmail;
    private EditText etConfirmarSenha;
    private EditText etSenha;
    private Button btnEntrar;
    private EditText etLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        etNome = findViewById(R.id.etCNome);
        etEmail = findViewById(R.id.etCEmail);
        etSenha = findViewById(R.id.etCSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
        //btnEntrar = findViewById(R.id.btCEntrar);

//        btnEntrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String nome = etNome.getText().toString();
//                String senha = etSenha.getText().toString();
//
//                Login login = new Login();
//                login.setNome(nome);
//                login.setSenha(senha);
//                Call<Login> call = new RetrofitConfig().getLoginService().fazerLogin(login);
//
//                call.enqueue(new Callback<Login>() {
//                    @Override
//                    public void onResponse(Call<Login> call, Response<Login> response) {
//                        if(response.isSuccessful()){
//                            Login dados = response.body();
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Login> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "Não foi possível enviar dados", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });
//
    }

    public void btnCEntrar (View view){
       String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String confSenha = etConfirmarSenha.getText().toString();

       if(nome.equals(null) || email.equals(null) || senha.equals(null) || confSenha.equals(null)) {
           Toast.makeText(this, "Campos não preenchidos", Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

}
