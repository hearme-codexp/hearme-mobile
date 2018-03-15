package br.senai.sp.informatica.mobile.apphearme.service;

import java.util.List;

import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.model.Login;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by CodeXP on 13/03/2018.
 */

public interface HistoricoService {
    @GET("historicos")
    public Call<List<Historico>> listarHistoricos();
}
