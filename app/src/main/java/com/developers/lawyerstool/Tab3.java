package com.developers.lawyerstool;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {


    public Tab3() {
        // Required empty public constructor
    }
    EditText number,court;
    Button courtcase,arbit;
    Spinner s1,s2,s3;
    TextView t1,t2;
    private Calendar myCalendar1 = Calendar.getInstance();
    private Button next2;
    EditText e1,e2,relsec,lim;
    int flag;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar1.set(Calendar.YEAR, year);
            myCalendar1.set(Calendar.MONTH, monthOfYear);
            myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar1.set(Calendar.YEAR, year);
            myCalendar1.set(Calendar.MONTH, monthOfYear);
            myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel1();
        }

    };
    Realm realm;
    String regno,incourt,rel,limdate,isevidence,evidencevia,crossreq,witness,dateofc,sections;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_tab3, container, false);
         RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
         Realm.setDefaultConfiguration(realmConfig);
         courtcase=(Button)v2.findViewById(R.id.button3);
         arbit=(Button)v2.findViewById(R.id.button4);
         number=(EditText)v2.findViewById(R.id.input_reg);
         court=(EditText)v2.findViewById(R.id.input_court);
         s1=(Spinner)v2.findViewById(R.id.spinner);
         s2=(Spinner)v2.findViewById(R.id.spinner2);
         s3=(Spinner)v2.findViewById(R.id.spinner3);
         t1=(TextView)v2.findViewById(R.id.textView1);
         t2=(TextView)v2.findViewById(R.id.textView2);
         e1=(EditText)v2.findViewById(R.id.input_wit);
         e2=(EditText)v2.findViewById(R.id.input_cross);
         relsec=(EditText)v2.findViewById(R.id.input_sec);
         lim=(EditText)v2.findViewById(R.id.input_limdate);
         next2=(Button)v2.findViewById(R.id.next2);
         e2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new DatePickerDialog(getActivity(), date1, myCalendar1
                         .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                         myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
             }
         });
         lim.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 new DatePickerDialog(getActivity(), date, myCalendar1
                         .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                         myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
             }
         });
        courtcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number.setHint("Registration number");
                court.setHint("In the court of");
                flag=0;
            }
        });
         arbit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 number.setHint("Claim number");
                 court.setHint("Arbitrators");
                 flag=1;
             }
         });
         String a[]={"Yes","No"};
         ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,a);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         s1.setAdapter(adapter);
         String b[]={"Affidavit","otherwise"};
         ArrayAdapter ad=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,b);
         ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         s2.setAdapter(ad);
         s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (position == 1) {
                     t1.setVisibility(View.GONE);
                     s2.setVisibility(View.GONE);
                     t2.setVisibility(View.GONE);
                     s3.setVisibility(View.GONE);
                     e1.setVisibility(View.GONE);
                     e2.setVisibility(View.GONE);

                 }
                 if (position == 0) {
                     t2.setVisibility(View.VISIBLE);
                     e1.setVisibility(View.VISIBLE);
                     e2.setVisibility(View.VISIBLE);
                     s3.setVisibility(View.VISIBLE);
                     t1.setVisibility(View.VISIBLE);
                     s2.setVisibility(View.VISIBLE);
                 }

             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
         s3.setAdapter(adapter);
         s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (position == 1) {
                     e1.setVisibility(View.GONE);
                     e2.setVisibility(View.GONE);
                 }
                 if (position == 0) {
                     e1.setVisibility(View.VISIBLE);
                     e2.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
        // Data.setdatatab2(regno,incourt,limdate,isevidence,evidencevia,crossreq,witness,dateofc);
         next2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 sections=relsec.getText().toString();
                 regno=number.getText().toString();
                 incourt=court.getText().toString();
                 rel=relsec.getText().toString();
                 limdate=lim.getText().toString();
                 isevidence=s1.getSelectedItem().toString();
                 evidencevia=s2.getSelectedItem().toString();
                 crossreq=s3.getSelectedItem().toString();
                 witness=e1.getText().toString();
                 dateofc=e2.getText().toString();
                 if(sections.trim().length()==0 || limdate.trim().length()==0 || isevidence.trim().length()==0 ){
                     Toast.makeText(getActivity(),"Fill out the above fields first!!",Toast.LENGTH_SHORT).show();
                 }
                 else{
                     realm=Realm.getDefaultInstance();
                     realm.executeTransaction(new Realm.Transaction() {
                         @Override
                         public void execute(Realm realm) {
                             Case realmCase1=((MainActivity)getActivity()).getOb();
                             realmCase1.setRegno(regno);
                             realmCase1.setIncourt(incourt);
                             realmCase1.setLimdate(limdate);
                             realmCase1.setIsevidence(isevidence);
                             realmCase1.setEvidencevia(evidencevia);
                             realmCase1.setCrossreq(crossreq);
                             realmCase1.setWitness(witness);
                             realmCase1.setDateofc(dateofc);
                             realmCase1.setFlag(flag);
                             realmCase1.setSections(sections);
                         }
                     });
                     ((MainActivity)getActivity()).setCurrentItem (3, true);
                 }
             }
         });
         return v2;


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        lim.setText(sdf.format(myCalendar1.getTime()));
    }

    private void updateLabel1() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        e2.setText(sdf.format(myCalendar1.getTime()));
    }

}
