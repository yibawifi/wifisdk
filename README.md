[English version](README_en.md)

# Yiba WiFi SDk 说明文档1.1.4
 1、app项目为WiFi SDK的demo项目
 
### SDK Demo下载

  - 在[易跋官网](http://global.18wifibank.com/)或者点击[这里](http://global.18wifibank.com/)下载最新版的sdk demo.
  - 注意demo是用Android Studio构建，需要用Android Studio打开，用eclipse打开会发生错误。

### Android Studio快速集成
 
#### 1、在你的 app Module 的build.gradle文件中添加
```
 dependencies {
     compile 'com.yiba:wifisdk:latest.release'
     //其中latest.release指代最新版本号，也可以指定明确的版本号，例如1.0.0
 }
```
 查看最新明确版本号，点击 [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)
 
 最新版本的的示例如图所示:
 ![](http://i2.buimg.com/567571/69c62f08ef69e2a9.png)
 
 明确版本号的示例如图所示
 ![](http://i2.buimg.com/567571/0abc4b2047ec2952.png)
 
#### 2、打开WiFi界面
```
 Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
 startActivity( intent );
```
 如图所示
 ![](http://i2.buimg.com/567571/976f52477c954722.png)
 
 
#### 3、常用API说明（注意：必须在Android主线程调用以下API，否则会出错）
```
 //获取free wifi 通知的开关状态。true:打开  false:关闭。默认情况下为true
  WiFiSDKManager.getInstance().getFreeWifiToggle( this ) ;
 
 //设置free wifi 通知的开关状态。true：打开   false:关闭。默认情况下为true
 //如果你设置为false,那么你将收不到任何关于free wifi的通知提醒。
 WiFiSDKManager.getInstance().setFreeWifiToggle( this , true );
 
 //获取open wifi 通知的开关状态。true：打开   false:关闭。默认情况下为true
  WiFiSDKManager.getInstance().getOpenWifiToggle( this ) ;
 
 //设置open wifi 通知的开关状态。true：打开   false:关闭。默认情况下为true
 //如果你设置为false,那么你将收不到任何关于open wifi的通知提醒。
 WiFiSDKManager.getInstance().setOpenWifiToggle( this , true );
 
 //获取常驻通知栏的显示状态。true：显示   false:关闭。默认情况下为true
 WiFiSDKManager.getInstance().getNotificationToggle( this ) ;
 
 //设置常驻通知栏的显示状态。true：显示   false:关闭。默认情况下为true。
 //如果你设置为false,那么手机通知栏里面关于wifi 的常驻通知将消失。
 WiFiSDKManager.getInstance().setNotificationToggle( this , true );
```
 
#### 4、混淆说明
 1、如果你的项目包名是：com.yiba.sdk , 请必须在混淆文件中添加
```
 -keep public class com.yiba.sdk.R$*{
  public static final int *;
 }
```
 2、必须添加在混淆文件中添加
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
