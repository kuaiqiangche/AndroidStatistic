package com.cocoa.piccolo.gradletest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.gradletest.AbsActivity
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/12/12 20:23
 */
public class AbsActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }
}
