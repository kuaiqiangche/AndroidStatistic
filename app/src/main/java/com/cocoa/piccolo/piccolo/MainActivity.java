package com.cocoa.piccolo.piccolo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        tv.setOnClickListener(this);

        rg.setOnCheckedChangeListener(this);
        new ListView(this).setOnItemClickListener(this);
        try {
            Class ccc = Integer.TYPE;
            Log.e("=-----", Integer.TYPE+ "-----");
            Log.e("=--ccc---", ccc.getName()+ "-----");

        } catch (Exception e) {
            Log.e("=-----", e.toString() + "-----");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.d("-------", "---" + i);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
