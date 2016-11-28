package com.cocoa.piccolo.piccolo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {


    private Context mContext;


    private ListView listview;
    private ImageView img;


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

        Log.e("----", DeviceInfoUtils.getInfo(this).toString() + "-----");
        Log.e("----", NetworkUtils.getNetworkState(this).toString() + "-----");


        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);
//        Glide.with(this).load("http://upload-images.jianshu.io/upload_images/944365-207a738cb165a2da.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240")
//                .centerCrop().into(img);

        img.setImageResource(R.mipmap.ic_launcher);


        listview = (ListView) findViewById(R.id.listview);


        String adapterData[] = {"123", "123123123", "1323123"};


        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, adapterData));

        listview.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String className = view.getClass().getName();

        Log.e("----", className + "----");

        View rootView  = view.getRootView();

        Log.e("----", view.getRootView().getId()+"---");

        if (view.getId() == R.id.tv) {
            startActivity(new Intent(this, A.class));
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {


        try {
            Log.e("---", img.toString());

        } catch (Exception e) {
            Log.e("---", e.toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.e("----",adapterView.getClass().getName());
            Log.e("----",view.getClass().getName());
    }
}
