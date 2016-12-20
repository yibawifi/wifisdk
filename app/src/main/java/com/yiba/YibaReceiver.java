package com.yiba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import www.yiba.com.wifisdk.manager.WiFiSDKManager;

public class YibaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( intent == null ) return;
        if( TextUtils.equals(intent.getAction(), "yiba_activity_back_onclick")) {
            //the back image is clicked
            WiFiSDKManager.getInstance().setYibaActivityFinish( context );
        }
    }
}
