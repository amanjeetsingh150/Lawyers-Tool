package com.developers.lawyerstool;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

/**
 * Created by Amanjeet Singh on 28-Jun-16.
 */
public class CustomDialog extends Dialog {
    private TextView update;
    private EditText e;
    private Button upd;
    private String s;
    String pattern = "dd/MM/yy";
    Calendar myCalendar = Calendar.getInstance();
    private final static String TAG=CustomDialog.class.getSimpleName();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private String titleofCase;
    private int pos;
    private static int iiid,flag=0,iiid1,flag1=0,iiid2;
    private CustomListAdapter customListAdapter;
    private ArrayList vals;
    public CustomDialog(Context context,String s,String titleofCase,int pos,CustomListAdapter customListAdapter,ArrayList vals) {
        super(context);
        this.s=s;
        this.titleofCase=titleofCase;
        this.pos=pos;
        this.customListAdapter=customListAdapter;
        this.vals=vals;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);
        update=(TextView)findViewById(R.id.tx);
        upd=(Button)findViewById(R.id.update);
        e=(EditText)findViewById(R.id.txtName);
        update.setText(s);
        if(s.equals("Date of Filing: ")|s.equals("Limitation Date: ")||s.equals("Date of Cross Examination: ")||s.equals("Date of Next Hearing: ")){
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=e.getText().toString();
                updateVals(a);
                dismiss();
            }
        });
    }
private void updateVals(String b){
    Realm realm=Realm.getInstance(getContext());
    Case c=realm.where(Case.class).equalTo("casetitle",titleofCase).findFirst();
          switch (pos){
              case 0:
                  realm.beginTransaction();
                  c.setCasetitle(b);
                  realm.commitTransaction();
                  break;
              case 1:
                  realm.beginTransaction();
                  c.setCasename(b);
                  realm.commitTransaction();
                  break;

              case 2:
                  realm.beginTransaction();
                  c.setPet(b);
                  realm.commitTransaction();
                  break;

              case 3:
                  realm.beginTransaction();
                  c.setPetname(b);
                  realm.commitTransaction();
                  break;

              case 4:
                  realm.beginTransaction();
                  c.setPetcontact(b);
                  realm.commitTransaction();
                  break;

              case 5:
                  realm.beginTransaction();
                  c.setPetmail(b);
                  realm.commitTransaction();
                  break;

              case 6:
                  realm.beginTransaction();
                  c.setResp(b);
                  realm.commitTransaction();
                  break;

              case 7:
                  realm.beginTransaction();
                  c.setRespname(b);
                  realm.commitTransaction();
                  break;

              case 8:
                  realm.beginTransaction();
                  c.setRespcontact(b);
                  realm.commitTransaction();
                  break;

              case 9:
                  realm.beginTransaction();
                  c.setRespmail(b);
                  realm.commitTransaction();
                  break;

              case 10:
                  realm.beginTransaction();
                  c.setDateoffiling(b);
                  realm.commitTransaction();
                  break;
              case 11:
                  realm.beginTransaction();
                  c.setRegno(b);
                  realm.commitTransaction();
                  break;

              case 12:
                  realm.beginTransaction();
                  c.setIncourt(b);
                  realm.commitTransaction();
                  break;

              case 13:
                  realm.beginTransaction();
                  c.setLimdate(b);
                  realm.commitTransaction();
                  break;

              case 14:
                  realm.beginTransaction();
                  c.setIsevidence(b);
                  realm.commitTransaction();
                  break;

              case 15:
                  realm.beginTransaction();
                  c.setEvidencevia(b);
                  realm.commitTransaction();
                  break;
              case 16:
                  realm.beginTransaction();
                  c.setCrossreq(b);
                  realm.commitTransaction();
                  break;

              case 17:
                  realm.beginTransaction();
                  c.setWitness(b);
                  realm.commitTransaction();
                  break;

              case 18:
                  realm.beginTransaction();
                  c.setDateofc(b);
                  realm.commitTransaction();
                  break;

              case 19:
                  realm.beginTransaction();
                  c.setNexthearing(b);
                  String uphearing=c.getNexthearing();
                  Log.d(TAG,"HIIIIIIIIIIIIIIIIII "+uphearing);
                  long d1=check1(uphearing);
                  Log.d(TAG,"Days Left,.................... "+d1);
                  if(d1>=1 && d1<3){
                      setAlarminUpdate(uphearing);
                  }
                  if(d1>=3 && d1<7){
                      setAlarminUpdate(uphearing);
                      setAlarminUpdate3(uphearing);
                  }
                  if(d1>=7){
                      setAlarminUpdate(uphearing);
                      setAlarminUpdate2(uphearing);
                      setAlarminUpdate3(uphearing);
                  }
                  realm.commitTransaction();
                  realm.beginTransaction();
                  if(flag==0){
                      Calender cal=realm.createObject(Calender.class);
                      cal.setIid(SetArr.geti());
                      cal.setMtitle(titleofCase);
                      cal.setDateh(b);
                      iiid=cal.getIid();
                  }
                  else{
                      Calender cl=realm.where(Calender.class).equalTo("iid",iiid1).findFirst();
                      cl.setDateh(b);
                  }
                  Log.d(TAG,"HIIIIIIIIIIIIIIIIII2");
                  realm.commitTransaction();
                  break;

              case 20:
                  realm.beginTransaction();
                  c.setBusi(b);
                  realm.commitTransaction();
                  realm.beginTransaction();
                  System.out.println("saaaaaaaaaaaaaaaaale "+iiid);
                  if(iiid!=0){
                      Calender cl=realm.where(Calender.class).equalTo("iid",iiid).findFirst();
                      String t=cl.getMtitle();
                      cl.setBusiness(b);
                  }
                  else{
                      Calender mCal=realm.createObject(Calender.class);
                      mCal.setIid(SetArr.geti());
                      mCal.setMtitle(titleofCase);
                      mCal.setBusiness(b);
                      iiid1=mCal.getIid();
                      flag=1;
                  }
                  realm.commitTransaction();
                  break;

              case 21:
                  realm.beginTransaction();
                  c.setActionclient(b);
                  realm.commitTransaction();
                  break;
              case 22:
                  realm.beginTransaction();
                  c.setActionlawyer(b);
                  realm.commitTransaction();
                  break;

              case 23:
                  realm.beginTransaction();
                  c.setActionopp(b);
                  realm.commitTransaction();
                  break;

              case 24:
                  realm.beginTransaction();
                  c.setSections(b);
                  realm.commitTransaction();
                  break;
              case 25:
                  realm.beginTransaction();
                  c.setDateofdoc(b);
                  realm.commitTransaction();
                  realm.beginTransaction();
                  if(flag1==0){
                      DocsFile file=realm.createObject(DocsFile.class);
                      file.setIid1(SetArr.geti1());
                      file.setTitleof(titleofCase);
                      file.setDateofdo(b);
                      iiid2=file.getIid1();
                  }
                  realm.commitTransaction();
                  break;
              case 26:
                  realm.beginTransaction();
                  c.setDocfiled(b);
                  realm.commitTransaction();
                  realm.beginTransaction();
                  if(iiid2!=0){
                      DocsFile d=realm.where(DocsFile.class).equalTo("iid1",iiid2).findFirst();
                      d.setDocfile(b);
                  }
                  realm.commitTransaction();
                  break;
          }
        realm.close();
        synchronized (vals){
         vals.notify();
          }
        customListAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(),"Information Updated!!\nSelect Case once again from the List to see results.",Toast.LENGTH_SHORT).show();
}
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        e.setText(sdf.format(myCalendar.getTime()));
    }
    private long check1(String nex){
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
            Toast.makeText(getContext(), "Error "+e+" "+end+" "+start, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG,"DAYSSSSSS "+days);
        return days;
    }
    private String today(){
        String date=new SimpleDateFormat(pattern).format(new Date());
        return date;
    }
    private void setAlarminUpdate(String next){
        Date ne=null;
        SimpleDateFormat patt=new SimpleDateFormat(pattern);
        try{
            ne=patt.parse(next);
        }
        catch(Exception e){
            Log.d(TAG,"Exception "+e) ;
        }
        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(ne);
        calendar.add(Calendar.DATE, -1);
        Date dateBefore1Day = calendar.getTime();
        calendar.setTime(dateBefore1Day);
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getContext(),MyReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getContext(),3,alarmintent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        Log.d(TAG,"Alarm is set!!!!");
    }
    private void setAlarminUpdate2(String next){
        Date n=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{
            n=sdf.parse(next);
        }
        catch (Exception e){
            Log.d(TAG,"Exception "+e);
        }
        AlarmManager alarmManager=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Calendar cal=Calendar.getInstance();
        cal.setTime(n);
        cal.add(Calendar.DATE,-7);
        Date before7days=cal.getTime();
        cal.setTime(before7days);
        cal.set(Calendar.HOUR_OF_DAY,8);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getContext(),MyReceiver.class);
        PendingIntent pendingIntent1=PendingIntent.getBroadcast(getContext(),4,alarmintent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1);
        Log.d(TAG,"ALARM before of 7 days set...");
    }
    private void setAlarminUpdate3(String he){
        Date h=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{
            h=sdf.parse(he);
        }
        catch (Exception e){
            Log.d(TAG,"Exception "+e);
        }
        AlarmManager alarm=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Calendar c=Calendar.getInstance();
        c.setTime(h);
        c.add(Calendar.DATE,-3);
        Date before3days=c.getTime();
        c.setTime(before3days);
        c.set(Calendar.HOUR_OF_DAY,8);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        Intent alarmintent=new Intent(getContext(),MyReceiver.class);
        PendingIntent pendingIntent2=PendingIntent.getBroadcast(getContext(),2,alarmintent,0);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2);
        Log.d(TAG,"Alarm of 3 days before are set....");
    }

}
