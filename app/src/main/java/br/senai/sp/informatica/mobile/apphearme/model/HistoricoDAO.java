package br.senai.sp.informatica.mobile.apphearme.model;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoricoDAO {
    public static HistoricoDAO manager = new HistoricoDAO();
    private List<Historico> lista;
    private long id = 0;

    private HistoricoDAO(){
        lista = new ArrayList<>();
        lista.add(new Historico(id++, "06/03/2018 - 11:23:04"));
        lista.add(new Historico(id++, "06/03/2018 - 12:24:05"));
        lista.add(new Historico(id++, "06/03/2018 - 13:25:06"));
        lista.add(new Historico(id++, "06/03/2018 - 14:26:07"));
        lista.add(new Historico(id++, "06/03/2018 - 15:27:08"));
        lista.add(new Historico(id++, "06/03/2018 - 16:28:09"));
        lista.add(new Historico(id++, "06/03/2018 - 17:29:10"));
        lista.add(new Historico(id++, "06/03/2018 - 18:30:11"));
        lista.add(new Historico(id++, "06/03/2018 - 19:31:12"));
    }

    public List<Historico> getLista(){
        return lista;
    }

    public Historico getHist(final Long id){
        Historico objHist = null;
        for(Historico obj : lista){
            if(obj.getId() == id){
                objHist = obj;
                break;
            }
        }
        return objHist;
    }
}
