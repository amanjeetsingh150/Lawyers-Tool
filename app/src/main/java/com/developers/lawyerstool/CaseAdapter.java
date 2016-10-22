package com.developers.lawyerstool;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Amanjeet Singh on 16-Jun-16.
 */
public class CaseAdapter extends RealmRecyclerViewAdapter<Case> {
    private class CaseViewHolder extends RecyclerView.ViewHolder {
        public TextView caset;
        public CaseViewHolder(View view) {
            super(view);
            caset = (TextView) view.findViewById(R.id.titler);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_case, parent, false);
        return new CaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CaseViewHolder evh = (CaseViewHolder) viewHolder;
        Case cases = getItem(position);
        evh.caset.setText(cases.getCasetitle());

    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }
}
