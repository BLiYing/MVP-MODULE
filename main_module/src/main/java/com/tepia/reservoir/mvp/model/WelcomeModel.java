package com.tepia.reservoir.mvp.model;

import android.content.Context;

import com.tepia.reservoir.mvp.contract.WelcomeContract;


import java.security.interfaces.RSAPublicKey;



/**
 * Created by khj on 2018-3-12.
 */
public class WelcomeModel implements WelcomeContract.Model {

    private RSAPublicKey publicKey;

    @Override
    public void getServerTime(Context context, final WelcomeContract.Presenter presenter) {


    }


}
