package com.developers.lawyerstool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Schedule extends AppCompatActivity {
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        realm=Realm.getDefaultInstance();
        RealmQuery query=realm.where(Calender.class);
        RealmResults results=query.findAll();
        System.out.println("sasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa--> "+results);
    }
}
