[中文](README.md)
## We Share WiFi SDK Development Instructions 2.1.5

### Update logs v2.1.5

- Fixed the GPS bugs and optimize save battery.
- Add Notification broadcast with found avaliable WiFi, you can use your UI at the notifications.
- Fixed some other bugs.

### SDK Demo



 - Download the newest version of SDK demo from the[ Official Website Of WeShare SDK](http://www.pegasus-mobile.com/index_en.html) or [Github](https://github.com/yibawifi/wifisdk)
 - Note: Since the SDK demo is structured of Android Studio, the demo should be opened with Android Studio. Error will occur if open with eclipse.



### Android studio package integration steps

#### 1、In the build.gradle document of your app module, add:
```
dependencies {
    compile 'com.yiba:wifisdk:latest.release'
    //The latest.release refers to the newest version number.
    //It may also refers to the a specific version number, for example 2.1.5
}
```
To check the newest version number, please click [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)

Please see a sample of the newest version as follows:
![](http://i2.buimg.com/567571/69c62f08ef69e2a9.png)



#### 2、Open the WiFi interface
```
Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
startActivity( intent );
```
See below for an example
![](http://i2.buimg.com/567571/976f52477c954722.png)


#### 3、Common API instructions(Please note: The API below must be used in Android main Thread or mistakes will appear)
```
//Set token SDK.  please go to the official website (http://www.pegasus-mobile.com/) to get.
//If the token is not set, you will not be able to get shared WiFi; SDK's partial functionality will not be used.
WiFiSDKManager.getInstance().setToken( this , "your app token");

//Get shared WiFi notification status:
//true:On     false:Off     Default setting:true.
 WiFiSDKManager.getInstance().getSharedWifiToggle( this ) ;

//Get shared WiFi notification status:
//true：On    false:Off     Default setting:true
//If you set as false, then you will not receive any notifications of free WiFi.
WiFiSDKManager.getInstance().setSharedWifiToggle( this , true );

//Get open WiFi notification status:
//true：On   false:Off     Default setting:true
 WiFiSDKManager.getInstance().getOpenWifiToggle( this ) ;

//Turn on/off open WiFi notification status:
//true：On   false:False   Default setting:true
//If you set as false, then you will not receive any notifications of open WiFi.
WiFiSDKManager.getInstance().setOpenWifiToggle( this , true );

//Get the notification bar display status:
//true：On   false:Off     Default setting:true
WiFiSDKManager.getInstance().getNotificationToggle( this ) ;

//Set the notification bar display status:
//true：On   false:Off     Default setting:true
//If the display status is set as false, the wifi notification in the notification center will disappear.
WiFiSDKManager.getInstance().setNotificationToggle( this , true );

//finish YIbaWifiActivity
WiFiSDKManager.getInstance().setYibaActivityFinish( context );

```

#### 4、Custom Notification

##### 4.1  If you need a custom notification, you need to close the default notification

```
//Close Share WiFi notification
WiFiSDKManager.getInstance().setSharedWifiToggle( this , false );
//Close Open WiFi notification
WiFiSDKManager.getInstance().setOpenWifiToggle( this , false );
```

##### 4.2  Custom BroadcastReceive to receive SDK passed over the JSON data
```
public class NotificationReveicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //IntentConstant.ACTION_YIBA_WIFI_NOCIFICATION is the value of the Intent of our definition of action
        if (IntentConstant.ACTION_YIBA_WIFI_NOCIFICATION.equals(action)) {
            //IntentConstant.NOTIFICATION_JSON_DATA is the data we passed through Intent, the data is JSON
            //JSON data sample：{"type":0,"count":1}
            String json = intent.getStringExtra(IntentConstant.NOTIFICATION_JSON_DATA);
            ...
            //You can write custom notification code here
        }
    }
}
```

##### 4.3  JSON data sample
```
JSON data sample, as：{"type":0,"count":1}

type value of 0, indicating that the Share WiFi type; type value of 1, indicating that the type of Open WiFi.

The count value indicates the number of WiFi types.
```

##### 4.4  Registering the Custom BroadcastReceive in AndroidManifest.xml
```
<receiver android:name=".NotificationReveicer">
    <intent-filter>
        <action android:name="com.yiba.action.ACTION_YIBA_WIFI_NOTIFICATION"/>
    </intent-filter>
</receiver>
```


#### 5、Custom UI
##### 5.1、 How to customize the title UI in WiFi list interface
In your project create Layout XML File: yiba_wifi_custom_layout.xml

For example:

![](/app/img/pic1.png)

Please note:

>1. The name of the Layout XML File must be: yiba_wifi_custom_layout.xml and cannot be changed.

>2. Your project ID must contain android:id="@+id/yiba_custom_layou_fram". This cannot be deleted or changed. The function of the ID is to control the click event of back button and finish current activity

##### 5.2、 How to customize the title of setting interface

In your project create a Layout XML File: yiba_wifi_custom_setting_layout.xml

For example:

![](/app/img/pic4.png)

Please note:

>1. The name of the Layout XML File must be yiba_wifi_custom_setting_layout.xml and cannot be changed

>2. Your project ID must contain android:id="@+id/yiba_custom_layou_setting". This cannot be deleted or changed. The function of the ID is to control the click event of back button and finish current activity.

##### 5.3、 How to receive the click event for the returned icon

>1、Add in your app module's AndroidManifest.xml

```
       <!--  Customize the broadcast receiver -->

        <receiver
            android:name=".YibaReceiver"
            android:enabled="true"
            android:exported="true">

            <intent-filter >
                <action android:name="yiba_activity_back_onclick"/>
            </intent-filter>

        </receiver>

```

> 2、Create a new YibaReceiver class

```
public class YibaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( intent == null ) return;
        if( TextUtils.equals(intent.getAction(), "yiba_activity_back_onclick")) {
            //the back image is clicked
        }
    }
}

```



#### 6、Code obfuscation instructions

##### 6.1、Please make sure to add these in the `proguard-rules.pro` document:
```
-keep class android.support.v7.**{*;}
-keep class android.support.v4.**{*;}

```









