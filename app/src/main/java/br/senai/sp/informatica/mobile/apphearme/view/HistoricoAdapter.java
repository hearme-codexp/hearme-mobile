package br.senai.sp.informatica.mobile.apphearme.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.senai.sp.informatica.mobile.apphearme.R;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;
import br.senai.sp.informatica.mobile.apphearme.model.HistoricoDAO;

public class HistoricoAdapter extends BaseAdapter{
    private HistoricoDAO dao = HistoricoDAO.manager;
    private Map<Integer, Long> mapa;

    @Override
    public int getCount() {
        return mapa.size();
    }

    @Override
    public Object getItem(int i) {
        return dao.getHist((long)i);
    }

    @Override
    public long getItemId(int i) {
        return mapa.get(i);
    }

    public HistoricoAdapter(){
        criarMapa();
    }

    private void criarMapa() {
        mapa = new HashMap<>();
        List<Historico> lista = dao.getLista();

        for(int linha = 0; linha < lista.size(); linha ++){
            Historico hist = lista.get(linha);
            mapa.put(linha, hist.getId());
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConstraintLayout layout;
        if(view == null){
            Context ctx = viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new ConstraintLayout(ctx);
            inflater.inflate(R.layout.home_aux, layout);
        }else{
            layout = (ConstraintLayout)view;
        }

        TextView tvHorario = layout.findViewById(R.id.tvHorario);
        Historico obj = dao.getHist(mapa.get(i));
        tvHorario.setText(obj.getData());

        return layout;
    }
}
