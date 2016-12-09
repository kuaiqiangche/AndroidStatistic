package com.cocoa.piccolo.gradletest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        rg.setOnCheckedChangeListener(this);

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);

        img.setImageResource(R.mipmap.ic_launcher);

        listview = (ListView) findViewById(R.id.listview);

        String adapterData[] = {"123", "123123123", "1323123"};

        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, adapterData));

        listview.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
