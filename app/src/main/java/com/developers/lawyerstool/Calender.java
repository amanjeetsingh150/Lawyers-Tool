package com.developers.lawyerstool;

import io.realm.RealmObject;

/**
 * Created by Amanjeet Singh on 25-Aug-16.
 */
public class Calender extends RealmObject {
    private String dateh;
    private String business;
    private int iid;
    private String mtitle;
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }


    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getDateh() {
        return dateh;
    }

    public void setDateh(String dateh) {
        this.dateh = dateh;
    }

}
