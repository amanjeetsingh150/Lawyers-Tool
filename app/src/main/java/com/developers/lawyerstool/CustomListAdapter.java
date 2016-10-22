package com.developers.lawyerstool;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 28-Jun-16.
 */
public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<String> m=new ArrayList<>();
    private ArrayList<String> n=new ArrayList<>();
    public CustomListAdapter(Activity activity, ArrayList m,ArrayList n){
        this.activity=activity;
        this.m=m;
        this.n=n;
    }
    @Override
    public int getCount() {
        return n.size();
    }

    @Override
    public Object getItem(int position) {
        return n.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.card_view_row,null);
        String a=n.get(position);
        String b=m.get(position);
        TextView tt=(TextView)convertView.findViewById(R.id.textView50);
        TextView tt1=(TextView)convertView.findViewById(R.id.textView51);
        tt.setText(b);
        tt1.setText(a);
        return convertView;
    }
}
