package com.developers.lawyerstool;

import io.realm.RealmObject;

/**
 * Created by Amanjeet Singh on 17-Oct-16.
 */
public class DocsFile extends RealmObject {
    private String dateofdo;
    private String docfile;
    private String titleof;
    private int iid1;
    public String getDateofdo() {
        return dateofdo;
    }

    public void setDateofdo(String dateofdo) {
        this.dateofdo = dateofdo;
    }

    public String getDocfile() {
        return docfile;
    }

    public void setDocfile(String docfile) {
        this.docfile = docfile;
    }

    public String getTitleof() {
        return titleof;
    }

    public void setTitleof(String titleof) {
        this.titleof = titleof;
    }

    public int getIid1() {
        return iid1;
    }

    public void setIid1(int iid1) {
        this.iid1 = iid1;
    }
}
