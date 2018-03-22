package br.senai.sp.informatica.mobile.apphearme.domain;

/**
 * Created by CodeXP on 21/03/2018.
 */

public interface ApiResponse<T> {
    void onSuccess(T data);
    void onError(Throwable t);
}
