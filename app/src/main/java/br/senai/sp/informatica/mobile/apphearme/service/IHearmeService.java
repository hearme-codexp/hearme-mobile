package br.senai.sp.informatica.mobile.apphearme.service;

import java.util.List;

import br.senai.sp.informatica.mobile.apphearme.model.Alerta;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by CodeXP on 21/03/2018.
 */

public interface IHearmeService {
    //interface que fala qual a atividade que vou fazer: mandr ou recer dados
    //pegando dados de historico do back
    //Pegando dados de historico
    @GET("Historico/cliente/{id}")
    Call<List<Historico>> listaHistorico(@Path("id") int clienteId);

    @POST("Historico/cliente/{id}")
    Call<Historico> enviaDadoHistorico(@Path("id") int clienteId, @Body Historico dadoHistorico);

    @POST("Cadastrar/App")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);
}