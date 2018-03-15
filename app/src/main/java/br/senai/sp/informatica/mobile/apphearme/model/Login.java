package br.senai.sp.informatica.mobile.apphearme.model;

import org.json.JSONObject;

/**
 * Created by CodeXP on 14/03/2018.
 */

public class Login {
    private String nome;
    private String senha;

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", senha=" + senha +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
