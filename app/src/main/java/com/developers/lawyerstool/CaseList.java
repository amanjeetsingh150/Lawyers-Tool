package com.developers.lawyerstool;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.developers.lawyerstool.RecyclerItemClickListener.*;

public class CaseList extends AppCompatActivity {
    private CaseAdapter adapter;
    private Realm realm;
    private RecyclerView rv;
    private static String name;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm= Realm.getInstance(this);
        setContentView(R.layout.activity_case_list);
        adapter=new CaseAdapter();
        rv=(RecyclerView)findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        f++;
        if(f>1){
            finish();
        }
        RealmResults<Case> events = realm.where(Case.class).findAll();
        RealmCasesAdapter realmAdapter = new RealmCasesAdapter(getApplicationContext(), events, true);
        // Set the data and tell the RecyclerView to draw
        adapter.setRealmAdapter(realmAdapter);
        rv.addOnItemTouchListener(new RecyclerItemClickListener(rv, getApplicationContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView t = (TextView) view.findViewById(R.id.titler);
                RealmResults<Case> result = realm.where(Case.class).equalTo("casetitle", (String) t.getText()).findAll();
                Intent intent = new Intent(getApplicationContext(), CaseInfo.class);
                for (Case c : result) {
                    String title = (String) t.getText();
                    intent.putExtra("title", title);
                    String name = c.getCasename();
                    intent.putExtra("name", name);
                    String petit = c.getPet();
                    intent.putExtra("petitioner", petit);
                    String petitnaame = c.getPetname();
                    intent.putExtra("petname", petitnaame);
                    String petCont = c.getPetcontact();
                    intent.putExtra("petcontact", petCont);
                    String petm = c.getPetmail();
                    intent.putExtra("petmail", petm);
                    String resp = c.getResp();
                    intent.putExtra("respondent", resp);
                    String respn = c.getRespname();
                    intent.putExtra("respn", respn);
                    String respc = c.getRespcontact();
                    intent.putExtra("respcont", respc);
                    String respm = c.getRespmail();
                    intent.putExtra("respmail", respm);
                    String dateof = c.getDateoffiling();
                    intent.putExtra("dof", dateof);
                    int ff = c.getFlag();
                    String reg = c.getRegno();
                    String incourt = c.getIncourt();
                    System.out.println("aaaaaaaaaa-----------------------> " + incourt);
                    intent.putExtra("abc", incourt);
                    intent.putExtra("regno", reg);
                    intent.putExtra("flag", ff);
                    String limd = c.getLimdate();
                    intent.putExtra("limdate", limd);
                    String isled = c.getIsevidence();
                    intent.putExtra("isevled", isled);
                    String evia = c.getEvidencevia();
                    intent.putExtra("evia", evia);
                    String crossr = c.getCrossreq();
                    intent.putExtra("cross", crossr);
                    String wit = c.getWitness();
                    intent.putExtra("witness", wit);
                    String dateofcross = c.getDateofc();
                    intent.putExtra("dateofcre", dateofcross);
                    String dateofhe = c.getNexthearing();
                    intent.putExtra("datehe", dateofhe);
                    String bus = c.getBusi();
                    intent.putExtra("busi", bus);
                    String acbyc = c.getActionclient();
                    intent.putExtra("actionc", acbyc);
                    String acbyl = c.getActionlawyer();
                    intent.putExtra("actionl", acbyl);
                    String acbyopp = c.getActionopp();
                    intent.putExtra("actiono", acbyopp);
                    String se = c.getSections();
                    intent.putExtra("sec", se);
                    String dateofdoc=c.getDateofdoc();
                    intent.putExtra("dateofdoc",dateofdoc);
                    String doc=c.getDocfiled();
                    intent.putExtra("docfiled",doc);
                }
                startActivity(intent);

            }
        }, new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final View V, int position) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CaseList.this);
                alertDialog.setTitle("Confirm Delete...");
                alertDialog.setMessage("Are you sure you want delete this?");
                alertDialog.setIcon(R.mipmap.court48);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                     TextView t= (TextView) V.findViewById(R.id.titler);
                     String ti= (String) t.getText();
                        System.out.println("ye delet karega-----------> "+ti);
                        Case cc=realm.where(Case.class).equalTo("casetitle",ti).findFirst();
                        realm.beginTransaction();
                        cc.removeFromRealm();
                        realm.commitTransaction();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Case Successfully Deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        }));


    }

}
