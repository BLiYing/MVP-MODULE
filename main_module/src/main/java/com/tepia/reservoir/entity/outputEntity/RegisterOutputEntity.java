package com.tepia.reservoir.entity.outputEntity;

import com.tepia.reservoir.entity.HttpResponse;

/**
 * Created by khj on 2018-3-13.
 */

public class RegisterOutputEntity extends HttpResponse{
    private String RETMSG;
    private String TIME;
    private String RESSEQ;

    public String getRETMSG() {
        return RETMSG;
    }

    public void setRETMSG(String RETMSG) {
        this.RETMSG = RETMSG;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getRESSEQ() {
        return RESSEQ;
    }

    public void setRESSEQ(String RESSEQ) {
        this.RESSEQ = RESSEQ;
    }
}
