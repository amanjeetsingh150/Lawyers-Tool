package com.developers.lawyerstool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class CaseNotList extends AppCompatActivity {
    private String a;
    private Realm re;
    private ArrayList<String> values=new ArrayList<>();
    private ArrayList<String> m=new ArrayList<>();
    private ListView list;
    private CustomListAdapter custom;
    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        list=(ListView)findViewById(R.id.listView2);
        m.add("Case Title: ");
        m.add("Case name: ");
        m.add("Petitioner: ");
        m.add("Name of advocate of Petitioner: ");
        m.add("Contact of advocate of Petitioner: ");
        m.add("Email of advocate of Petitioner: ");
        m.add("Respondent: ");
        m.add("Name of advocate of Respondent: ");
        m.add("Contact of advocate of Respondent: ");
        m.add("Email of advocate of Respondent:");
        m.add("Date of Filing: ");
        m.add("Reg/Claim number: ");
        m.add("Incourt/arbitrators");
        m.add("Limitation Date");
        m.add("Is Evidence to be Led");
        m.add("Evidence Via");
        m.add("Cross Examination Required");
        m.add("Name of Witnesses");
        m.add("Date of Cross Examination");
        m.add("Date of Next Hearing");
        m.add("Business on Next Hearing");
        m.add("Action required by client");
        m.add("Action required by Lawyer");
        m.add("Action by opposition");
        m.add("Relevant Sections");
        Bundle b=getIntent().getExtras();
        a=b.getString("casetit");
        re=Realm.getInstance(this);
        re.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Case> resul=re.where(Case.class).equalTo("casetitle",a).findAll();
                for(Case o:resul){
                    String title= o.getCasetitle();
                    values.add(title);
                    String name = o.getCasename();
                    values.add(name);
                    String petit=o.getPet();
                    values.add(petit);
                    String petitnaame=o.getPetname();
                    values.add(petitnaame);
                    String petCont=o.getPetcontact();
                    values.add(petCont);
                    String petm=o.getPetmail();
                    values.add(petm);
                    String resp=o.getResp();
                    values.add(resp);
                    String respn=o.getRespname();
                    values.add(respn);
                    String respc=o.getRespcontact();
                    values.add(respc);
                    String respm=o.getRespmail();
                    values.add(respm);
                    String dateof=o.getDateoffiling();
                    values.add(dateof);
                    int ff=o.getFlag();
                    if(ff==0){
                        m.set(11,"Registration number: ");
                        m.set(12,"In court of: ");
                    }
                    else{
                        m.set(11,"Claim number: ");
                        m.set(12,"Arbitrators: ");
                    }
                    String reg=o.getRegno();
                    values.add(reg);
                    String incourt=o.getIncourt();
                    values.add(incourt);
                    System.out.println("aaaaaaaaaa-----------------------> "+incourt);
                    String limd=o.getLimdate();
                    values.add(limd);
                    String isled=o.getIsevidence();
                    values.add(isled);
                    String evia=o.getEvidencevia();
                    if(isled.equals("Yes")){
                        values.add(evia);
                    }
                    else{
                        values.add("N/A");
                    }
                    String crossr=o.getCrossreq();
                    values.add(crossr);
                    String wit=o.getWitness();
                    String dateofcross=o.getDateofc();
                    if(crossr.equals("Yes")){
                        values.add(wit);
                        values.add(dateofcross);
                    }
                    else{
                        values.add("N/A");
                        values.add("N/A");
                    }
                    String dateofhe=o.getNexthearing();
                    values.add(dateofhe);
                    String bus=o.getBusi();
                    values.add(bus);
                    String acbyc=o.getActionclient();
                    values.add(acbyc);
                    String acbyl=o.getActionlawyer();
                    values.add(acbyl);
                    String acbyopp=o.getActionopp();
                    values.add(acbyopp);
                    String seca=o.getSections();
                    values.add(seca);
                }
            }
        });
        custom=new CustomListAdapter(this,m,values);
        list.setAdapter(custom);
    }
}
