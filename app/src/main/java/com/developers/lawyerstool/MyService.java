package com.developers.lawyerstool;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyService extends Service {
    private Realm realm;
    private Date end1=null;
    private int count=0;
    ArrayList<String> arr=new ArrayList<String>();
    private String pattern = "dd/MM/yy";
    private static final String TAG=MyService.class.getSimpleName();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service instaniatingggggggggggggggggggg");
        diff();
        stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show();
    }
    public void diff(){
        realm=Realm.getInstance(this);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Case> rr=realm.where(Case.class).findAll();
                for(Case cc:rr){
                    String date =cc.getNexthearing();
                    end1 = endate(date);
                    long p=check(end1);
                    if(p==3 ||  p==7 || p==1){
                        count++;
                        arr.add(cc.getCasetitle());
                    }
                }
             SetArr.setM(arr);
            }
        });
        if(count>0){
            Log.d(TAG,"Total cases coming "+count);
            notification();
        }
    }
    private void notification(){
        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent in=new Intent(this,CaseNotice.class);
        PendingIntent pi= PendingIntent.getActivity(this,0,in,0);
        NotificationCompat.Builder notify=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.court48)
                .setContentTitle("Hello!!")
                .setContentInfo("You have Next Hearing coming!!")
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL);
        nm.notify(111,notify.build());
    }
    private Date endate(String hearing){
        Date end=null;
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try{
            end=sdf.parse(hearing);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return end;
    }

    private long check(Date end){
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
            Toast.makeText(this, "Error "+e+" "+end+" "+start, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG,"DAYSSSSSS "+days);
        return days;
    }

    public String today(){
        String date=new SimpleDateFormat(pattern).format(new Date());
        return date;
    }
}
