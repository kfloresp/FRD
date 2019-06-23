package com.teamallqu.frd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ad_lista extends BaseAdapter {
    Activity activity;
    List<c_ficha> listdata;
    LayoutInflater inflater;

    public ad_lista(Activity activity, List<c_ficha> listdata) {
        this.activity = activity;
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item_lista,null);
        TextView txup = itemview.findViewById(R.id.item_historia);
        TextView txtexto = itemview.findViewById(R.id.item_nombres);
        txup.setText(listdata.get(i).getNumhistoria());
        txtexto.setText(listdata.get(i).getNombreape());
        return itemview;
    }
}
