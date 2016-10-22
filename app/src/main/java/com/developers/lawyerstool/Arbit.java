package com.developers.lawyerstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class Arbit extends AppCompatActivity {
    private Button schedule;
    private ListView schedulelist;
    private Realm mRealm;
    private ArrayList<String> hearings,busin,dateofdocs,docsfil;
    private static final String TAG=Arbit.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbit);
        TextView ardisp= (TextView) findViewById(R.id.textView52);
        schedulelist=(ListView)findViewById(R.id.listView3);
        hearings=new ArrayList<String>();
        busin=new ArrayList<String>();
        dateofdocs=new ArrayList<String>();
        docsfil=new ArrayList<String>();
        schedulelist.setVisibility(View.GONE);
        Bundle ex=getIntent().getExtras();
        if(ex!=null){
            int pos=ex.getInt("position");
            String title=ex.getString("title");
            switch(pos){
                case 12:
                    String ar = ex.getString("arb");
                    ar = ar.replace(",", "\n");
                    ardisp.setText(ar);
                    break;
                case 20:
                    schedulelist.setVisibility(View.VISIBLE);
                    ardisp.setText("Schedule:");
                    mRealm=Realm.getInstance(this);
                    Log.d(TAG,"This is the title of case "+title);
                    RealmResults<Calender> result = mRealm.where(Calender.class).equalTo("mtitle",title).findAll();
                    for(Calender ca:result){
                        String next=ca.getDateh();
                        String business=ca.getBusiness();
                        hearings.add(next);
                        busin.add(business);
                    }
                    CustomListAdapter c=new CustomListAdapter(this,hearings,busin);
                    schedulelist.setAdapter(c);
                    break;
                case 24:
                    String re = ex.getString("rel");
                    re = re.replace(",", "\n");
                    ardisp.setText(re);
                    break;
                case 26:
                    schedulelist.setVisibility(View.VISIBLE);
                    ardisp.setText("Schedule:");
                    mRealm=Realm.getInstance(this);
                    Log.d(TAG,"This is the title of case "+title);
                    RealmResults<DocsFile> results=mRealm.where(DocsFile.class).equalTo("titleof",title).findAll();
                    for(DocsFile d:results){
                        String dates=d.getDateofdo();
                        String docs=d.getDocfile();
                        dateofdocs.add(dates);
                        docsfil.add(docs);
                    }
                    CustomListAdapter c1=new CustomListAdapter(this,dateofdocs,docsfil);
                    schedulelist.setAdapter(c1);
                    break;
                default:
                    String some=ex.getString("text");
                    ardisp.setText(some);
                    break;
            }
        }
    }
}
