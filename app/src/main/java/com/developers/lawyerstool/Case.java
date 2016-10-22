package com.developers.lawyerstool;

import io.realm.RealmObject;

/**
 * Created by Amanjeet Singh on 15-Jun-16.
 */
public class Case extends RealmObject {

    private String casetitle;
    private String sections;
    private String casename;
    private String pet;
    private String petname;
    private String petcontact;
    private String petmail;
    private String resp;
    private String respname;
    private String respcontact;
    private String respmail;
    private String dateoffiling;
    private String regno;
    private String incourt;
    private String limdate;
    private String nexthearing;
    private String busi;
    private String actionclient;
    private String actionlawyer;
    private String actionopp;
    private String isevidence;
    private String evidencevia;
    private String crossreq;
    private String witness;
    private String dateofc;
    private int flag;
    private String dateofdoc;
    private String docfiled;
    public String getSections() {
        return sections;
    }
    public void setSections(String sections) {
        this.sections = sections;
    }
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getCasetitle() {
        return casetitle;
    }

    public void setCasetitle(String casetitle) {
        this.casetitle = casetitle;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetcontact() {
        return petcontact;
    }

    public void setPetcontact(String petcontact) {
        this.petcontact = petcontact;
    }

    public String getPetmail() {
        return petmail;
    }

    public void setPetmail(String petmail) {
        this.petmail = petmail;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getRespname() {
        return respname;
    }

    public void setRespname(String respname) {
        this.respname = respname;
    }

    public String getRespcontact() {
        return respcontact;
    }

    public void setRespcontact(String respcontact) {
        this.respcontact = respcontact;
    }

    public String getRespmail() {
        return respmail;
    }

    public void setRespmail(String respmail) {
        this.respmail = respmail;
    }

    public String getDateoffiling() {
        return dateoffiling;
    }

    public void setDateoffiling(String dateoffiling) {
        this.dateoffiling = dateoffiling;
    }

    public String getIncourt() {
        return incourt;
    }

    public void setIncourt(String incourt) {
        this.incourt = incourt;
    }

    public String getLimdate() {
        return limdate;
    }

    public void setLimdate(String limdate) {
        this.limdate = limdate;
    }

    public String getNexthearing() {
        return nexthearing;
    }

    public void setNexthearing(String nexthearing) {
        this.nexthearing = nexthearing;
    }

    public String getBusi() {
        return busi;
    }

    public void setBusi(String busi) {
        this.busi = busi;
    }

    public String getActionclient() {
        return actionclient;
    }

    public void setActionclient(String actionclient) {
        this.actionclient = actionclient;
    }

    public String getActionlawyer() {
        return actionlawyer;
    }

    public void setActionlawyer(String actionlawyer) {
        this.actionlawyer = actionlawyer;
    }

    public String getActionopp() {
        return actionopp;
    }

    public void setActionopp(String actionopp) {
        this.actionopp = actionopp;
    }

    public String getIsevidence() {
        return isevidence;
    }

    public void setIsevidence(String isevidence) {
        this.isevidence = isevidence;
    }

    public String getEvidencevia() {
        return evidencevia;
    }

    public void setEvidencevia(String evidencevia) {
        this.evidencevia = evidencevia;
    }

    public String getCrossreq() {
        return crossreq;
    }

    public void setCrossreq(String crossreq) {
        this.crossreq = crossreq;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getDateofc() {
        return dateofc;
    }

    public void setDateofc(String dateofc) {
        this.dateofc = dateofc;
    }

    public String getDateofdoc() {
        return dateofdoc;
    }

    public void setDateofdoc(String dateofdoc) {
        this.dateofdoc = dateofdoc;
    }

    public String getDocfiled() {
        return docfiled;
    }

    public void setDocfiled(String docfiled) {
        this.docfiled = docfiled;
    }

}
