package br.senai.sp.informatica.mobile.apphearme.service;

import java.io.IOException;
import java.util.List;

import br.senai.sp.informatica.mobile.apphearme.domain.ApiResponse;
import br.senai.sp.informatica.mobile.apphearme.model.Alerta;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CodeXP on 21/03/2018.
 */

public class HearmeRestService {
    private IHearmeService service;
    //Metodos que fazem as comunicações

    public HearmeRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hearme-app.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(IHearmeService.class);
    }

    public void listaHistorico(final ApiResponse<List<Historico>> callback){
        Call<List<Historico>> dadoshistorico = service.listaHistorico(1);
        dadoshistorico.enqueue(new Callback<List<Historico>>() {
            @Override
            public void onResponse(Call<List<Historico>> call, Response<List<Historico>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Historico>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void enviarDadoHistorico (Historico dadoHistorico,final ApiResponse<Historico> callback) {
        Call<Historico> novodado = service.enviaDadoHistorico(dadoHistorico);
        novodado.enqueue(new Callback<Historico>() {
            @Override
            public void onResponse(Call<Historico> call, Response<Historico> response) {
                if(response.code() != 200) {
                    try {
                        callback.onError(new Throwable("Reposta de erro: " + response.code() + " " + response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Historico> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void cadastrarUsuario(Usuario usuario, final ApiResponse<Usuario> callback){
        Call<Usuario> novousuaruio = service.cadastrarUsuario(usuario);
        novousuaruio.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                callback.onError(t);
            }
        });
    }



}
