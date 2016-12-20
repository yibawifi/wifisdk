[中文](README.md)
## Yiba WiFi SDK Integration Instructions 2.1.1
 1、Demo project of app named WiFi SDK
 
 2、Note: Since the demo is structured of Android Studio，it needs to be opened with Android Studio. Error will occur when open with Eclipse. 
 
 3、[Yiba official website](http://global.18wifibank.com/) 
 
---

## Android studio package integration steps
 
### 1、In the build.gradle document of your app module, add:
```
 dependencies {
     compile 'com.yiba:wifisdk:latest.release'
     //latest.release refers to the newest version number. 
     //It may also refer to the exact version number such as 2.1.1
     }
```
 To check the newst version number, please click  [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)
 
 Please see below for an example of the newest version:
 ![](http://i2.buimg.com/567571/69c62f08ef69e2a9.png)
 
 
### 2、Open the WiFi interface
```
 Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
 startActivity( intent );
```
 See below for an example
 ![](http://i2.buimg.com/567571/976f52477c954722.png)
 
 
### 3、Common API instructions(Please note: The API below must be used in Android main Thread or mistakes will appear)
```
//Set token SDK. If there is no token, please go to the official website (http://www.pegasus-mobile.com/) to get.
//If the token is not set, you will not be able to get shared WiFi; SDK's partial functionality will not be used.
WiFiSDKManager.getInstance().setToken( this , "your app token");

 //To access shared WiFi notification switch status: true: On  false: Off  Default setting:true 
  WiFiSDKManager.getInstance().getSharedWifiToggle( this ) ;
 
 //To set the shared WiFi notification switch status:  true: On   false: Off   Default setting: true
 //If you set as false, then you will not receive any notifications of shared WiFi 
 WiFiSDKManager.getInstance().setSharedWifiToggle( this , true );
 
 //To access the open WiFi notification switch status: true: On   false:Off  Default setting: true
  WiFiSDKManager.getInstance().getOpenWifiToggle( this ) ;
 
 //To set the open WiFi notification switch status:  true: On    false: Off  Default setting: true
 //If you set as false, then you will not receive any notifications of open WiFi.
 WiFiSDKManager.getInstance().setOpenWifiToggle( this , true );
 
 //To access the display status of “permanent notification bar”   true: Display  false: Off     Default setting: true
 WiFiSDKManager.getInstance().getNotificationToggle( this ) ;
 
 //To set the display status of “permanent notification bar” : true: Display  false: Off    Default setting: true 
 //If you set as false, then the notification of WiFi in the permanent notification bar will disappear. 
 WiFiSDKManager.getInstance().setNotificationToggle( this , true );
 
 //finish YIbaWifiActivity
 WiFiSDKManager.getInstance().setYibaActivityFinish( context );
 
```

### 4、Custom UI
#### 4.1、 How to customize the title UI in WiFi list interface
 In your project create Layout XML File: yiba_wifi_custom_layout.xml 
 
 For example:
 
![](http://p1.bpimg.com/567571/b403f52d99a8bef7.png)
 
 Please note:
 
 >1. The name of the Layout XML File must be: yiba_wifi_custom_layout.xml and cannot be changed.
 
 >2. Your project ID must contain android:id="@+id/yiba_custom_layou_fram". This cannot be deleted or changed. The function of the ID is to control the click event of back button and finish current activity 
 
#### 4.2、 How to customize the title of setting interface
 
 In your project create a Layout XML File: yiba_wifi_custom_setting_layout.xml
 
 For example:
 
![](http://p1.bpimg.com/567571/c93e46395ce7b9fe.png)
 
 Please note:
 
 >1. The name of the Layout XML File must be yiba_wifi_custom_setting_layout.xml and cannot be changed 
 
 >2. Your project ID must contain android:id="@+id/yiba_custom_layou_setting". This cannot be deleted or changed. The function of the ID is to control the click event of back button and finish current activity.
 
#### 4.3、 How to receive the click event for the returned icon

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
 
 
### 5、Proguard instructions 
#### 5.1、If the name of your project package is :com.yiba.sdk, please make sure to add :
```
 -keep public class com.yiba.sdk.R$*{
  public static final int *;
 }
```
#### 5.2、Please make sure to add these in the proguard document:
``` 
 -keep class www.yiba.com.wifisdk.**{*;}
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Fragment
 -keep public class * extends android.app.Service
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.app.Fragment
 -keep public class android.support.v4.** {*;}
 -keep public class * extends android.view.View {
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
     public void set*(...);
 }
``` 
