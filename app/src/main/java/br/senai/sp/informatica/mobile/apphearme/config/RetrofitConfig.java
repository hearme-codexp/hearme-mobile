package br.senai.sp.informatica.mobile.apphearme.config;

import br.senai.sp.informatica.mobile.apphearme.service.HistoricoService;
import br.senai.sp.informatica.mobile.apphearme.service.LoginService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by CodeXP on 13/03/2018.
 */

public class RetrofitConfig {

    public final Retrofit retrofit;
    private final LoginService loginService;
    private final HistoricoService historicoService;

    public RetrofitConfig(){

       this.retrofit =  new Retrofit.Builder()
                .baseUrl("Data Source=.\\HearMe.db").addConverterFactory(JacksonConverterFactory.create()).build();
        this.loginService = this.retrofit.create(LoginService.class);
        this.historicoService = this.retrofit.create(HistoricoService.class);
    }

    public LoginService getLoginService(){
        return loginService;
    }

    public HistoricoService getHistoricoService() {
        return historicoService;
    }
}
