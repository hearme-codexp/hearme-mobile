package br.senai.sp.informatica.mobile.apphearme.service;

import br.senai.sp.informatica.mobile.apphearme.model.Login;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by CodeXP on 13/03/2018.
 */

public interface LoginService {

    @POST("login")
    public Call<Login> fazerLogin(Login login);
}
