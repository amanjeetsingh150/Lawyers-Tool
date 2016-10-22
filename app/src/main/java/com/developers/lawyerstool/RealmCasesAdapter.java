package com.developers.lawyerstool;

import android.content.Context;

import io.realm.RealmResults;

/**
 * Created by Amanjeet Singh on 16-Jun-16.
 */
public class RealmCasesAdapter extends RealmModelAdapter<Case> {
    public RealmCasesAdapter(Context context, RealmResults<Case> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
