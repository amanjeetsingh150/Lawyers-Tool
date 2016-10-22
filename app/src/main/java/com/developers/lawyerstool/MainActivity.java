package com.developers.lawyerstool;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;
import io.realm.Realm;


public class MainActivity extends FragmentActivity {
    private static final int numpages=4;
    private ViewPager mPager;
    private PagerAdapter adapter;
    private Calendar c;
    private PendingIntent pendingIntent;
    Case cas;
    int g;
    private Realm rr;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar);
        t.setTitle("Lawyers Tool");
        mPager=(ViewPager)findViewById(R.id.pager);
        adapter=new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
    }
    public void setCurrentItem (int item, boolean smoothScroll) {
        mPager.setCurrentItem(item, smoothScroll);
    }

    public void setId(int id){
       g=id;
    }
    public int getId(){
        return g;
    }
    public void setOb(Case cas){
        this.cas=cas;
    }
    public Case getOb(){
        return cas;
    }
    public void setR(Realm rr){
        this.rr=rr;
    }
    public Realm getR(){
        return rr;
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                Tab1 tab1=new Tab1();
                return tab1;
            }
         else if(position==1){
                Tab2 tab2=new Tab2();
                return tab2;
            }
         else if(position==2){
                Tab3 tab3=new Tab3();
                return tab3;
            }
            else{
                Tab4 tab4=new Tab4();
                return tab4;

            }
        }

        @Override
        public int getCount() {
            return numpages;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
