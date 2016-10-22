package com.developers.lawyerstool;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.security.spec.ECField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab4 extends Fragment {
    private Button submit;
    Date end1=null;
    int count=0;
    RealmConfiguration realmConfig;
    String pattern = "dd/MM/yy";
    Calendar myCalendar2=Calendar.getInstance();
    private Realm realm;
    private PendingIntent pendingIntent;
    EditText nexth,business,aclient,alawyer,aopp,dateofdoc,docfile;
    String nexthearing,busi,actionclient,actionlawyer,actionopp,dateofdo,docsfiled;
    private static final String TAG=Tab4.class.getSimpleName();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, monthOfYear);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, monthOfYear);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel1();
        }
    };

    public Tab4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        // Inflate the layout for this fragment
        Realm.setDefaultConfiguration(realmConfig);
        View v3= inflater.inflate(R.layout.fragment_tab4, container, false);
        realm=Realm.getDefaultInstance();
        nexth= (EditText) v3.findViewById(R.id.input_he);
        business=(EditText) v3.findViewById(R.id.input_bus);
        aclient=(EditText) v3.findViewById(R.id.input_acc);
        alawyer=(EditText) v3.findViewById(R.id.input_acl);
        aopp=(EditText) v3.findViewById(R.id.input_aco);
        dateofdoc=(EditText)v3.findViewById(R.id.input_dateofdoc);
        docfile=(EditText)v3.findViewById(R.id.input_documents);
        submit=(Button)v3.findViewById(R.id.button5);
        nexth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        dateofdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date1,myCalendar2
                        .get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        nexthearing=nexth.getText().toString();
                        busi=business.getText().toString();
                        actionclient=aclient.getText().toString();
                        actionlawyer=alawyer.getText().toString();
                        actionopp=aopp.getText().toString();
                        dateofdo=dateofdoc.getText().toString();
                        docsfiled=docfile.getText().toString();
                        if(nexthearing.trim().length()==0 || busi.trim().length()==0 || actionclient.trim().length()==0 || actionlawyer.trim().length()==0 || actionopp.trim().length()==0 || dateofdo.trim().length()==0 || docsfiled.trim().length()==0 ){
                           Toast.makeText(getActivity(),"Fill out the above fields first!!!",Toast.LENGTH_SHORT).show();
                        }
                            Case realmCase2=((MainActivity)getActivity()).getOb();
                            realmCase2.setNexthearing(nexthearing);
                            realmCase2.setBusi(busi);
                            realmCase2.setActionclient(actionclient);
                            realmCase2.setActionlawyer(actionlawyer);
                            realmCase2.setActionopp(actionopp);
                            realmCase2.setDateofdoc(dateofdo);
                            realmCase2.setDocfiled(docsfiled);

                    }
                });
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String caseti=SetArr.getCasetitle();
                        Calender cal=realm.createObject(Calender.class);
                        cal.setMtitle(caseti);
                        cal.setDateh(nexthearing);
                        cal.setBusiness(busi);
                        System.out.println("vaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah");
                        RealmQuery query=realm.where(Calender.class);
                        RealmResults results=query.findAll();
                        System.out.println("sas--> "+results);
                    }
                });
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String caseti=SetArr.getCasetitle();
                        DocsFile doc=realm.createObject(DocsFile.class);
                        doc.setTitleof(caseti);
                        doc.setDateofdo(dateofdo);
                        doc.setDocfile(docsfiled);
                        RealmQuery query1=realm.where(DocsFile.class);
                        RealmResults results1= query1.findAll();
                        Log.d(TAG,"This is the docs "+results1);
                    }
                });
                long d=check(nexthearing);
                Log.d(TAG,"The difference is "+d);
                if(d>=1 && d<3){
                    setAlarm(nexthearing);
                }
                if(d>=3 && d<7){
                    setAlarm(nexthearing);
                    setAlarm3(nexthearing);
                }
                if(d>=7){
                    setAlarm(nexthearing);
                    setAlarm2(nexthearing);
                    setAlarm3(nexthearing);
                }
                Toast.makeText(getActivity(),"Case Registered",Toast.LENGTH_LONG).show();
                realm.close();
            }
        });
        return v3;
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        nexth.setText(sdf.format(myCalendar2.getTime()));
    }
    private void updateLabel1() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateofdoc.setText(sdf.format(myCalendar2.getTime()));
    }
    private void setAlarm(String next){
        Date ne=null;
        SimpleDateFormat patt=new SimpleDateFormat(pattern);
        try{
            ne=patt.parse(next);
        }
        catch(Exception e){
            Log.d(TAG,"Exception "+e) ;
        }
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(ne);
        calendar.add(Calendar.DATE, -1);
        Date dateBefore1Day = calendar.getTime();
        calendar.setTime(dateBefore1Day);
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getActivity(),MyReceiver.class);
        pendingIntent=PendingIntent.getBroadcast(getActivity(),0,alarmintent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        Log.d(TAG,"Alarm is set!!!!");
    }
    private void setAlarm2(String next){
        Date n=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{
            n=sdf.parse(next);
        }
        catch (Exception e){
            Log.d(TAG,"Exception "+e);
        }
        AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Calendar cal=Calendar.getInstance();
        cal.setTime(n);
        cal.add(Calendar.DATE,-7);
        Date before7days=cal.getTime();
        cal.setTime(before7days);
        cal.set(Calendar.HOUR_OF_DAY,8);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getActivity(),MyReceiver.class);
        PendingIntent pendingIntent1=PendingIntent.getBroadcast(getActivity(),1,alarmintent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1);
        Log.d(TAG,"ALARM before of 7 days set...");
    }
    private void setAlarm3(String he){
        Date h=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{
             h=sdf.parse(he);
        }
        catch (Exception e){
            Log.d(TAG,"Exception "+e);
        }
        AlarmManager alarm=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Calendar c=Calendar.getInstance();
        c.setTime(h);
        c.add(Calendar.DATE,-3);
        Date before3days=c.getTime();
        c.setTime(before3days);
        c.set(Calendar.HOUR_OF_DAY,8);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getActivity(),MyReceiver.class);
        PendingIntent pendingIntent2=PendingIntent.getBroadcast(getActivity(),2,alarmintent,0);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2);
        Log.d(TAG,"Alarm of 3 days before are set....");
    }
    private long check(String nex){
        Date end=null;
        SimpleDateFormat s=new SimpleDateFormat(pattern);
        try{
            end=s.parse(nex);
        }
        catch (Exception e){
            Log.d(TAG,"Exception "+e);
        }
        Date start=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String tod=today();
        long days=0;
        try{
            start=sdf.parse(tod);
            long diff=end.getTime()-start.getTime();
            long seconds=diff/1000;
            long minutes=seconds/60;
            long hours=minutes/60;
            days=hours/24;
        }
        catch(Exception e){
            Toast.makeText(getActivity(), "Error "+e+" "+end+" "+start, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG,"DAYSSSSSS "+days);
        return days;
    }
    private String today(){
        String date=new SimpleDateFormat(pattern).format(new Date());
        return date;
    }
}
