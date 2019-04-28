package com.ccd.harpalvaghela.sqlitedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.net.ConnectException;
import java.util.ArrayList;

public class adapter extends BaseAdapter {
    Context context;
    ArrayList<pojo> list;

    public adapter(Context context,ArrayList<pojo> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
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

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v=inflater.inflate(R.layout.single_layout,null);

        TextView id=(TextView)v.findViewById(R.id.txt_id);
        TextView name=(TextView)v.findViewById(R.id.txt_name);
        TextView email=(TextView)v.findViewById(R.id.txt_email);

        pojo p=(pojo)list.get(position);

        id.setText(p.getId());
        name.setText(p.getName());
        email.setText(p.getEmail());

        return v;
    }
}
