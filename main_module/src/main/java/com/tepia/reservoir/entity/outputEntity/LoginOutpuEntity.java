package com.tepia.reservoir.entity.outputEntity;

import com.tepia.reservoir.entity.HttpResponse;

/**
 * Created by khj on 2018-3-13.
 */

public class LoginOutpuEntity extends HttpResponse {
    private String RETMSG;
    private String USERID;
    private String INTEGRAL;
    private String STATE;
    private String RESSEQ;
    private String TEMINALID;

    public String getTEMINALID() {
        return TEMINALID;
    }

    public void setTEMINALID(String TEMINALID) {
        this.TEMINALID = TEMINALID;
    }

    public String getRETMSG() {
        return RETMSG;
    }

    public void setRETMSG(String RETMSG) {
        this.RETMSG = RETMSG;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getINTEGRAL() {
        return INTEGRAL;
    }

    public void setINTEGRAL(String INTEGRAL) {
        this.INTEGRAL = INTEGRAL;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getRESSEQ() {
        return RESSEQ;
    }

    public void setRESSEQ(String RESSEQ) {
        this.RESSEQ = RESSEQ;
    }
}
