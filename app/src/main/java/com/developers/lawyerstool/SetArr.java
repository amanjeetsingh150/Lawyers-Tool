package com.developers.lawyerstool;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 22-Jun-16.
 */
public class SetArr {
    private static ArrayList<String> m=new ArrayList<String>();
    private static String casetitle;
    private static int id=0;
    private static int id1=0;
    public static String getCasetitle() {
        return casetitle;
    }

    public static void setCasetitle(String casetitle) {
        SetArr.casetitle = casetitle;
    }

    public static void setM(ArrayList m){
        SetArr.m=m;
    }
    public static ArrayList getM(){
        return m;
    }
    public static int geti(){
        id=id+1;
        return id;
    }
    public static int geti1(){
        id1=id+1;
        return id1;
    }

}
