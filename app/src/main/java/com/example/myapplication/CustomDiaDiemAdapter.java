package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomDiaDiemAdapter extends BaseAdapter {
    private Context ctx;
    private int layoutitem;
    private ArrayList<String> arrayList;

    public CustomDiaDiemAdapter(Context ctx, int layoutitem, ArrayList<String> arrayList) {
        this.ctx = ctx;
        this.layoutitem = layoutitem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(layoutitem, parent, false);

        TextView tvTenDiaDiem = convertView.findViewById(R.id.tvTenDiaDiem);

        tvTenDiaDiem.setText(arrayList.get(position));
        return convertView;
    }
}
