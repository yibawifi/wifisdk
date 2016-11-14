package com.yiba;

import android.app.Application;

import www.yiba.com.wifisdk.manager.WiFiSDKManager;

/**
 * Created by yiba_zyj on 2016/11/14.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        WiFiSDKManager.getInstance().setToken( this , "FE251B8EA4404ABB9784DC9DF5ACBA60") ;
    }
}
