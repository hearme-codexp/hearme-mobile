package br.senai.sp.informatica.mobile.apphearme.service;

import java.util.List;

import br.senai.sp.informatica.mobile.apphearme.domain.ApiResponse;
import br.senai.sp.informatica.mobile.apphearme.model.Alerta;
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

    public void listaAlertas(final ApiResponse<List<Alerta>> callback){
        Call<List<Alerta>> alertas = service.listaAlertas();
        alertas.enqueue(new Callback<List<Alerta>>() {
            @Override
            public void onResponse(Call<List<Alerta>> call, Response<List<Alerta>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Alerta>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void cadastrarUsuario(Usuario usuario,final ApiResponse<Usuario> callback){
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
