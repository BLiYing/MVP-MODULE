package com.tepia.reservoir.entity.inputEntity;

/**
 * Created by khj on 2018-3-13.
 */

public class RegisterInputEntity {
    private String FUNCODE;
    private String REQSEQ;
    private String IMEI;
    private String MOBILE;
    private String PASSWORD;
    private String MSGCHECK;
    private String CALLTIME;
    private String SIGN;

    public String getFUNCODE() {
        return FUNCODE;
    }

    public void setFUNCODE(String FUNCODE) {
        this.FUNCODE = FUNCODE;
    }

    public String getREQSEQ() {
        return REQSEQ;
    }

    public void setREQSEQ(String REQSEQ) {
        this.REQSEQ = REQSEQ;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getMSGCHECK() {
        return MSGCHECK;
    }

    public void setMSGCHECK(String MSGCHECK) {
        this.MSGCHECK = MSGCHECK;
    }

    public String getCALLTIME() {
        return CALLTIME;
    }

    public void setCALLTIME(String CALLTIME) {
        this.CALLTIME = CALLTIME;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }
}
