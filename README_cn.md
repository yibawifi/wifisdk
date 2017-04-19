[English](README_en.md)


### 更新日志 v2.1.8
  - 优化添加SDK初始化进程.
  - 修复一些Bug.
  - 修复EAP网络Bug.

### SDK Demo下载



 - 在[WeShareSDK官网](http://www.pegasus-mobile.com/index_en.html)或者到[Github](https://github.com/yibawifi/wifisdk)下载最新版的sdk demo.
 - 注意demo是用Android Studio构建，需要用Android Studio打开，用eclipse打开会发生错误。



### Android Studio快速集成

#### 1、在你的 app Module 的build.gradle文件中添加
```

dependencies {
    compile 'com.yiba:wifisdk:latest.release'
    //其中latest.release指代最新版本号，也可以指定明确的版本号，例如2.1.5
}

```
查看最新明确版本号，点击 [jcenter](http://jcenter.bintray.com/com/yiba/wifisdk/)

最新版本的的示例如图所示:
![](/app/img/pic2.png)


#### 2、在你的Application类中添加SDK初始化
```
//添加这段代码到你的Application类的onCreate方法中
WiFiSDKManager.getInstance().init(getApplicationContext());
```

#### 3、打开WiFi界面
```
Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
startActivity( intent );
```
如图所示
![](/app/img/pic3.png)


#### 4、常用API说明（注意：必须在Android主线程调用以下API，否则会出错）
```

//设置SDK token 。如果没有token , 请到官网(http://www.pegasus-mobile.com/)获取。
//如果token没有设置，你将无法获取到 Shared WiFi；SDK的部分功能将无法使用。
 WiFiSDKManager.getInstance().setToken( this , "your app token");

//获取Shared wifi 通知的开关状态。true:打开  false:关闭。默认情况下为true
 WiFiSDKManager.getInstance().getSharedWifiToggle( this ) ;

//设置Shared wifi 通知的开关状态。true：打开   false:关闭。默认情况下为true
//如果你设置为false,那么你将收不到任何关于Shared wifi的通知提醒。
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

#### 5、自定义通知

##### 5.1 如果你想自定义通知，那么你要先将默认通知关闭：如

```
//关闭Share WiFi通知
WiFiSDKManager.getInstance().setSharedWifiToggle( this , false );
//关闭Open WiFi通知
WiFiSDKManager.getInstance().setOpenWifiToggle( this , false );
```

##### 5.2 通过自定义广播接收sdk传递过来的json数据：如
```
public class NotificationReveicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //IntentConstant.ACTION_YIBA_WIFI_NOCIFICATION是我们定义的Intent的action的值
        if (IntentConstant.ACTION_YIBA_WIFI_NOCIFICATION.equals(action)) {
            //IntentConstant.NOTIFICATION_JSON_DATA是我们通过Intent传递的数据，数据是json
            //json的数据格式：{"type":0,"count":1}
            String json = intent.getStringExtra(IntentConstant.NOTIFICATION_JSON_DATA);
            ...
            //你可以在这里写自定义通知代码
        }
    }
}
```

##### 5.3 json数据格式示例：
```
json的数据格式，如：{"type":0,"count":1}

type值为0，表示Share WiFi类型；type值为1，表示Open WiFi类型。

count值表示WiFi类型的个数。
```

##### 5.4 在AndroidManifest.xml中对广播进行注册
```
<receiver android:name=".NotificationReveicer">
    <intent-filter>
        <action android:name="com.yiba.action.ACTION_YIBA_WIFI_NOTIFICATION"/>
    </intent-filter>
</receiver>
```



#### 6、自定义UI
##### 6.1、 如何自定义Wifi列表界面的标题UI
在你的项目中新建 yiba_wifi_custom_layout.xml 布局文件

例如：

![](/app/img/pic1.png)

注意事项：

>1、布局文件的名字的必须是：yiba_wifi_custom_layout.xml , 不能修改。

>2、你的项目中的id必须有 android:id="@+id/yiba_custom_layou_fram" ， 不能删除、不能修改。这个id 作用是控制返回按钮的点击事件，销毁当前Activity。

##### 6.2、 如何自定义设置界面title

在你的项目中新建 yiba_wifi_custom_setting_layout.xml 布局文件。

例如：

![](/app/img/pic4.png)

注意事项：

>1、布局文件的名字的必须是：yiba_wifi_custom_setting_layout.xml , 不能修改.

>2、你的项目中的id必须有 android:id="@+id/yiba_custom_layou_setting" ， 不能删除、不能修改。这个id 作用是控制返回按钮的点击事件，销毁当前Activity.


##### 6.3、 如何接收返回图标的点击事件

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


#### 7、混淆说明

##### 7.1、必须添加在混淆文件中添加
```
-keep class android.support.v7.**{*;}
-keep class android.support.v4.**{*;}

```











