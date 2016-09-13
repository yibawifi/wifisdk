[English version](README.md)
# Yiba WiFi SDk Integration Instructions 1.1.4
 1、Demo project of app named WiFi SDK
 
### SDK Demo download

  - On [Yiba official website](http://global.18wifibank.com/) or click [here](http://global.18wifibank.com/)to download the newest version of sdk demo.
  - Note: Since the demo is structured of Android Studio，it needs to be opened with Android Studio. Error will occur when open with eclipse. 

### Android studio package integration steps
 
#### 1、1.In the build.gradle document of your app module, add:
```
 dependencies {
     compile 'com.yiba:wifisdk:latest.release'
     //latest.release refers to the newest version number. It may also refer to the exact version number such as 1.0.0}
```
 To check the newst version number, please click  [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)
 
 Please see below for an example of the newest version:
 ![](http://i2.buimg.com/567571/69c62f08ef69e2a9.png)
 
  Please see below for an example of the newest version:
 ![](http://i2.buimg.com/567571/0abc4b2047ec2952.png)
 
#### 2、2.Open the WiFi interface
```
 Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
 startActivity( intent );
```
 See below for an example
 ![](http://i2.buimg.com/567571/976f52477c954722.png)
 
 
#### 3、Common API instructions(Please note: The API below must be used in Android main Thread or mistakes will appear)
```
 //To access free WiFi notification switch status: true: On  false: Off  Default setting:true 
  WiFiSDKManager.getInstance().getFreeWifiToggle( this ) ;
 
 //To set the free WiFi notification switch status:  true: On   false: Off   Default setting: true
 //If you set as false, then you will not receive any notifications of free WiFi 
 WiFiSDKManager.getInstance().setFreeWifiToggle( this , true );
 
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
```
 
#### 4、Proguard instructions 
 1、(1)If the name of your project package is :com.yiba.sdk, please make sure to add :
```
 -keep public class com.yiba.sdk.R$*{
  public static final int *;
 }
```
 2、Please make sure to add these in the proguard document:
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
