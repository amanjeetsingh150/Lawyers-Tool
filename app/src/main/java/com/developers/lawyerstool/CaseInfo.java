package com.developers.lawyerstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CaseInfo extends AppCompatActivity {
    private ListView l;
    private Intent p;
    private CustomListAdapter customListAdapter;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        l=(ListView)findViewById(R.id.listView2);
        ArrayList<String> m=new ArrayList<>();
        p=new Intent(getApplicationContext(),Arbit.class);
        final ArrayList<String> vals=new ArrayList<>();
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
        m.add("Limitation Date: ");
        m.add("Is Evidence to be Led: ");
        m.add("Evidence Via: ");
        m.add("Cross Examination Required: ");
        m.add("Name of Witnesses: ");
        m.add("Date of Cross Examination: ");
        m.add("Date of Next Hearing: ");
        m.add("Business on Next Hearing: ");
        m.add("Action required by client: ");
        m.add("Action required by Lawyer: ");
        m.add("Action by opposition: ");
        m.add("Relevant Sections: ");
        m.add("Date of Document Filed: ");
        m.add("Document Filed at Date: ");
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            s1=extras.getString("title");
            vals.add(s1);
            String s2=extras.getString("name");
            vals.add(s2);
            String s3=extras.getString("petitioner");
            vals.add(s3);
            String s4=extras.getString("petname");
            vals.add(s4);
            String s5=extras.getString("petcontact");
            vals.add(s5);
            String s6=extras.getString("petmail");
            vals.add(s6);
            String s7=extras.getString("respondent");
            vals.add(s7);
            String s8=extras.getString("respn");
            vals.add(s8);
            String s9=extras.getString("respcont");
            vals.add(s9);
            String s10=extras.getString("respmail");
            vals.add(s10);
            String s11=extras.getString("dof");
            vals.add(s11);
            int fl=extras.getInt("flag");
            if(fl==0){
                m.set(11,"Registration number");
                m.set(12,"In court of: ");
            }
            else{
                m.set(11,"Claim number: ");
                m.set(12,"Arbitrators: ");
            }
            String s12=extras.getString("regno");
            String s13=extras.getString("abc");
            vals.add(s12);
            vals.add(s13);
            System.out.println("äaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa-------> "+s13);
            String s14=extras.getString("limdate");
            vals.add(s14);
            String s17=extras.getString("isevled");
            vals.add(s17);
            if(s17.equals("Yes")){
                String s18=extras.getString("evia");
                vals.add(s18);
                String s19=extras.getString("cross");
                vals.add(s19);
                if(s19.equals("Yes")){
                    String s20=extras.getString("witness");
                    vals.add(s20);
                    String s21=extras.getString("dateofcre");
                    vals.add(s21);
                }
                else{
                    vals.add("N/A");
                    vals.add("N/A");
                }
            }
            else{
                vals.add("N/A");
                vals.add("N/A");
                vals.add("N/A");
                vals.add("N/A");
            }
            String s22=extras.getString("datehe");
            vals.add(s22);
            String s23=extras.getString("busi");
            vals.add(s23);
            String s24=extras.getString("actionc");
            vals.add(s24);
            String s25=extras.getString("actionl");
            vals.add(s25);
            String s26=extras.getString("actiono");
            vals.add(s26);
            String s27=extras.getString("sec");
            vals.add(s27);
            String s28=extras.getString("dateofdoc");
            vals.add(s28);
            String s29=extras.getString("docfiled");
            vals.add(s29);
        }
        customListAdapter=new CustomListAdapter(this,m,vals);
        l.setAdapter(customListAdapter);
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               TextView t= (TextView) view.findViewById(R.id.textView50);
               String s= (String) t.getText();
               CustomDialog c=new CustomDialog(CaseInfo.this,s,vals.get(0),position,customListAdapter,vals);
               c.show();
               return true;
           }
       });
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t= (TextView) view.findViewById(R.id.textView51);
                p.putExtra("title",s1);
                if(position==12||position==24){
                    String arbitrators=vals.get(12);
                    String relsections=vals.get(24);
                    p.putExtra("position",position);
                    p.putExtra("arb",arbitrators);
                    p.putExtra("rel",relsections);
                }
                else if(position==20){
                   p.putExtra("position",position);
                }
                else if(position==26){
                 p.putExtra("position",position);
                }
                else{
                    p.putExtra("position",position);
                    p.putExtra("text",t.getText());
                }
                startActivity(p);
            }
        });

    }

}
