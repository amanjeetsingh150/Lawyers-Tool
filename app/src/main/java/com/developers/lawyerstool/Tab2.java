package com.developers.lawyerstool;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2 extends Fragment {


    public Tab2() {
        // Required empty public constructor
    }
//    Tab4 d=new Tab4();
    EditText title,cname,petitioner,padvocatename,padvocatecontact,padvocatemail,respondent,radvocatename,radvocatecontact,radvocatemail,dof;
    String casetitle,casename,pet,petname,petcontact,petmail,resp,respname,respcontact,respmail,dateoffiling;
    Realm realm;
    Calendar myCalendar = Calendar.getInstance();
    private Button next1;
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
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        Realm.setDefaultConfiguration(realmConfig);
        View v1= inflater.inflate(R.layout.fragment_tab2, container, false);
        title= (EditText) v1.findViewById(R.id.input_title);
        cname= (EditText) v1.findViewById(R.id.input_cname);
        petitioner=(EditText)v1.findViewById(R.id.input_pet);
        padvocatename=(EditText)v1.findViewById(R.id.input_advpet);
        padvocatecontact=(EditText)v1.findViewById(R.id.input_co);
        padvocatemail=(EditText)v1.findViewById(R.id.input_email);
        respondent=(EditText)v1.findViewById(R.id.input_resp);
        radvocatename=(EditText)v1.findViewById(R.id.input_resp_name);
        radvocatecontact=(EditText)v1.findViewById(R.id.input_coresp);
        radvocatemail=(EditText)v1.findViewById(R.id.input_resp_mail);
        dof=(EditText)v1.findViewById(R.id.input_date);
        next1=(Button)v1.findViewById(R.id.next1);
        dof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casetitle=title.getText().toString();
                casename=cname.getText().toString();
                pet=petitioner.getText().toString();
                petname=padvocatename.getText().toString();
                petcontact=padvocatecontact.getText().toString();
                petmail=padvocatemail.getText().toString();
                resp=respondent.getText().toString();
                respname=radvocatename.getText().toString();
                respcontact=radvocatecontact.getText().toString();
                respmail=radvocatemail.getText().toString();
                dateoffiling=dof.getText().toString();
                realm=Realm.getDefaultInstance();
                if(casetitle.trim().length()==0 || casename.trim().length()==0 || pet.trim().length()==0 || petname.trim().length()==0 || petcontact.trim().length()==0 || petmail.trim().length()==0 || resp.trim().length()==0 || respname.trim().length()==0 || respmail.trim().length()==0 || respcontact.trim().length()==0 || dateoffiling.trim().length()==0){
                    Toast.makeText(getActivity(),"Fill out the above fields first!!",Toast.LENGTH_SHORT).show();
                }
                else if(isEmail(petmail)==false || isEmail(respmail)==false){
                    Toast.makeText(getActivity(),"Fill out a valid Email!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Case realmCase=realm.createObject(Case.class);
                            realmCase.setCasetitle(casetitle);
                            SetArr.setCasetitle(casetitle);
                            realmCase.setCasename(casename);
                            realmCase.setPet(pet);
                            realmCase.setPetname(petname);
                            realmCase.setPetcontact(petcontact);
                            realmCase.setPetmail(petmail);
                            realmCase.setResp(resp);
                            realmCase.setRespname(respname);
                            realmCase.setRespcontact(respcontact);
                            realmCase.setRespmail(respmail);
                            realmCase.setDateoffiling(dateoffiling);
                            ((MainActivity)getActivity()).setOb(realmCase);
                        }
                    });
                    ((MainActivity)getActivity()).setCurrentItem (2, true);
                }
            }
        });


        return v1;
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dof.setText(sdf.format(myCalendar.getTime()));
    }
    private boolean isEmail(String email){
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean b=email.matches(EMAIL_PATTERN);
        return b;
    }

}
