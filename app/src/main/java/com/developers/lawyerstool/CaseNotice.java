package com.developers.lawyerstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CaseNotice extends AppCompatActivity {
    private ArrayList<String> ar=new ArrayList<String>();
    private ListView l;
    private String r;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_notice);
        l=(ListView)findViewById(R.id.listView);
        ar=SetArr.getM();
        if(ar!=null){
            System.out.println("aaaaaaaaaaaa-=-= "+ar);
        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,ar);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                r=ar.get(position);
                i=new Intent(getApplicationContext(),CaseNotList.class);
                i.putExtra("casetit",r);
                startActivity(i);
            }
        });





    }
}
