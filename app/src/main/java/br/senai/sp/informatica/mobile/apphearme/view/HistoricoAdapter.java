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

import br.senai.sp.informatica.mobile.apphearme.R;
import br.senai.sp.informatica.mobile.apphearme.model.Historico;

public class HistoricoAdapter extends BaseAdapter{
    private List<Historico> historicos;

    @Override
    public int getCount() {
        return historicos.size();
    }

    @Override
    public Object getItem(int i) {
        return historicos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return historicos.get(i).getId();
    }

    public HistoricoAdapter(List<Historico> historicos){
        this.historicos = historicos;
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
        Historico obj = historicos.get(i);
        tvHorario.setText((CharSequence) obj.getDataHorarioAlerta());

        return layout;
    }
}
