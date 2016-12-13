package com.cocoa.piccolo.gradletest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {


    private ListView listview;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv);
        TextView tvv = (TextView) findViewById(R.id.tvv);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
//        tv.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);

        img.setImageResource(R.mipmap.ic_launcher);

        listview = (ListView) findViewById(R.id.listview);

        String adapterData[] = {"123", "123123123", "1323123"};

        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, adapterData));

        listview.setOnItemClickListener(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.widget.Toast.makeText(MainActivity.this, "9999999",  android.widget.Toast.LENGTH_LONG).show();
            }
        });

        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "tvvtvv",  android.widget.Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Toast.makeText(this, "12312", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
