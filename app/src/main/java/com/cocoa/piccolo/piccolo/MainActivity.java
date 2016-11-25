package com.cocoa.piccolo.piccolo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {


    private Context mContext;

    private String msg = "[{\"page\": \"MainTabBarController\", \"path\": \"MainTabBarController\", \"wifi\": \"\", \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479977899}, {\"page\": \"KQCNavigationController\", \"path\": \"KQCNavigationController\", \"wifi\": \"\", \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479977899}, {\"page\": \"HomeViewController\", \"path\": \"HomeViewController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479977916}, {\"page\": \"ViewController\", \"path\": \"ViewController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479978054}, {\"page\": \"MainTabBarController\", \"path\": \"MainTabBarController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479978054}, {\"page\": \"KQCNavigationController\", \"path\": \"KQCNavigationController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479978054}, {\"page\": \"HomeViewController\", \"path\": \"HomeViewController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479978054}, {\"page\": \"HomeViewController\", \"path\": \"HomeViewController/UICollectionView/HomeBtnCollectionViewCell\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"HomeBtnCollectionViewCell\", \"app_build\": \"2.2.0\", \"eventName\": \"UIControl点击事件\", \"app_version\": \"15\", \"occurredTime\": 1479978073}, {\"page\": \"HomeViewController\", \"path\": \"HomeViewController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"HomeViewController\", \"app_build\": \"2.2.0\", \"eventName\": \"退出页面\", \"app_version\": \"15\", \"occurredTime\": 1479978074}, {\"page\": \"NewManagerCarViewController\", \"path\": \"NewManagerCarViewController\", \"wifi\": true, \"radio\": \"LTE\", \"content\": \"(null)\", \"app_build\": \"2.2.0\", \"eventName\": \"进入页面\", \"app_version\": \"15\", \"occurredTime\": 1479978074}, {\"common\": {\"os\": \"iOS\", \"uuid\": \"4EADBF0F-4DF8-4E5F-876C-851071F7670B\", \"carrier\": \"中国联通\", \"os_version\": \"iOS\", \"device_name\": \"heng的 iPhone\", \"manufacture\": \"Apple\", \"device_model\": \"iPhone7,2\", \"screen_width\": 375, \"screen_height\": 667}}]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        tv.setOnClickListener(this);
        mContext = this;
        rg.setOnCheckedChangeListener(this);
//        new ListView(this).setOnItemClickListener(this);

        Log.e("----", DeviceInfoUtils.getInfo(this).toString()+"-----");

    }

    @Override
    public void onClick(View view) {
            if(view.getId() ==R.id.tv){
                startActivity(new Intent(this,A.class));

            }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
