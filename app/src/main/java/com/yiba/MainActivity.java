package com.yiba;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import www.yiba.com.wifisdk.activity.YIbaWifiActivity;
import www.yiba.com.wifisdk.manager.WiFiSDKManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById( R.id.openWifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开wifi 界面
                Intent intent = new Intent( MainActivity.this , YIbaWifiActivity.class) ;
                startActivity( intent );
            }
        });

    }
}
