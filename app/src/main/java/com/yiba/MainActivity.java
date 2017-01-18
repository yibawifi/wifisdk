package com.yiba;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import www.yiba.com.wifisdk.activity.YIbaWifiActivity;
import www.yiba.com.wifisdk.ad.CustomerViewBuilder;
import www.yiba.com.wifisdk.manager.WiFiSDKManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBannerAd();
        findViewById( R.id.openWifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //打开wifi 界面
                Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
                startActivity( intent );
            }
        });


    }

    /**
     * 允许你添加一个 banner 广告到 wifi 列表。
     * 使用此功能请联系：support@pegasus-mobile.com
     */
    private void addBannerAd(){
        WiFiSDKManager.getInstance().setBannerAdInWifiList(MainActivity.this, new CustomerViewBuilder.CustomerAdView() {
            @Override
            public View createView(CustomerViewBuilder.NativeAdControl nativeAdControl) {
                LayoutInflater layoutInflater = LayoutInflater.from( MainActivity.this ) ;
                View view = layoutInflater.inflate( R.layout.banner_ad_layout , null ) ;
                nativeAdControl.show( getApplicationContext() );
                return view ;
            }
        })  ;
    }

}
