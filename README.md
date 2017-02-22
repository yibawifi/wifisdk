[English](README_en.md)


## Yiba WiFi SDK 说明文档2.1.4
 1、app项目为WiFi SDK的Demo项目
 
 2、注意Demo是用Android Studio构建，需要用Android Studio打开，用Eclipse打开会发生错误。
 
 3、[SDK官方网站](http://global.18wifibank.com/)

 4、SDK的接入技术方面的问题，请联系：`support@pegasus-mobile.com`

 5、商务合作需求，请联系：`sdk-bd@pegasus-mobile.com`


---


## Android Studio快速集成
 
### 1、在你的 app Module 的build.gradle文件中添加
```
 dependencies {
     compile 'com.yiba:wifisdk:latest.release'
     //其中latest.release指代最新版本号，也可以指定明确的版本号，例如2.1.4
 }
```
 查看最新明确版本号，点击 [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)
 
 最新版本的的示例如图所示:
 ![](http://i2.buimg.com/567571/69c62f08ef69e2a9.png)
 

### 2、打开WiFi界面
```
 Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
 startActivity( intent );
```
 如图所示
 ![](http://i2.buimg.com/567571/976f52477c954722.png)
 
 
### 3、常用API说明（注意：必须在Android主线程调用以下API，否则会出错）
```
//设置SDK token 。如果没有token , 请到官网(http://www.pegasus-mobile.com/)获取。
//如果token没有设置，你将无法获取到免费WiFi；SDK的部分功能将无法使用。
 WiFiSDKManager.getInstance().setToken( this , "your app token");

 //获取shared wifi 通知的开关状态。true:打开  false:关闭。默认情况下为true
  WiFiSDKManager.getInstance().getSharedWifiToggle( this ) ;
 
 //设置shared wifi 通知的开关状态。true：打开   false:关闭。默认情况下为true
 //如果你设置为false,那么你将收不到任何关于shared wifi的通知提醒。
 WiFiSDKManager.getInstance().setSharedWifiToggle( this , true );
 
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
 
 //销毁 YIbaWifiActivity
 WiFiSDKManager.getInstance().setYibaActivityFinish( context );
 
```

### 4、自定义UI
#### 4.1、 如何自定义Wifi列表界面的标题UI
在你的项目中新建 yiba_wifi_custom_layout.xml 布局文件

例如：

![](http://p1.bpimg.com/567571/b403f52d99a8bef7.png)

注意事项：

>1、布局文件的名字的必须是：yiba_wifi_custom_layout.xml , 不能修改。

>2、你的项目中的id必须有 android:id="@+id/yiba_custom_layou_fram" ， 不能删除、不能修改。这个id 作用是控制返回按钮的点击事件，销毁当前Activity。

#### 4.2、 如何自定义设置界面title

在你的项目中新建 yiba_wifi_custom_setting_layout.xml 布局文件。

例如：

![](http://p1.bpimg.com/567571/c93e46395ce7b9fe.png)

注意事项：

>1、布局文件的名字的必须是：yiba_wifi_custom_setting_layout.xml , 不能修改.

>2、你的项目中的id必须有 android:id="@+id/yiba_custom_layou_setting" ， 不能删除、不能修改。这个id 作用是控制返回按钮的点击事件，销毁当前Activity. 
 
#### 4.3、 如何接收返回图标的点击事件

>1、在你app module 的AndroidManifest.xml 中添加

```
       <!--  自定义广播接收器 -->

        <receiver
            android:name=".YibaReceiver"
            android:enabled="true"
            android:exported="true">

            <intent-filter >
                <action android:name="yiba_activity_back_onclick"/>
            </intent-filter>

        </receiver>

```

> 2、新建YibaReceiver类

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
 
### 5、混淆说明
```
-keep class android.support.v7.**{*;}
-keep class android.support.v4.**{*;}
```

